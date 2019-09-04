<img src="https://raw.githubusercontent.com/ctripcorp/apollo/master/doc/images/logo/logo-simple.png" alt="apollo-logo" width="60%">

# 基于Springboot的Apollo的基本使用

### Apollo简介

#### apollo(阿波罗)是携程框架部门研发的分布式配置中心，能够集中化管理应用不同环境、不同集群的配置、配置修改后能够实时推送到应用端，并且具备规范的权限、流程治理等特性，适用于微服务配置管理场景。

#### 服务端基于spring boot和 spring cloud 开发，打包后可以直接运行，不需要额外安装Tomcat等应用容器

#### Java客户端不依赖任何框架，能够运行于所有Java运行时环境，同时对Spring/Spring Boot环境也有较好的支持。



### 产品特征

- **统一管理不同环境、不同集群的配置**
- **配置修改实时生效(热发布) **
- **灰度发布 **
- **权限管理、发布审核、操作审计 **
- **客户端配置信息监控 **
- **提供Java和.NET原生客户端 **
- **提供开放平台Api **

### Apollo VS Spring Cloud Config

| 功能点         | Apollo                                            | Spring Cloud Config                             |
| -------------- | ------------------------------------------------- | ----------------------------------------------- |
| 配置界面       | 统一界面管理不同环境/集群配置                     | 无,通过git操作                                  |
| 配置生效时间   | 实时                                              | 重启生效,或者Refresh,或git hook + MQ扩展        |
| 版本管理       | 界面上直接提供发布历史和回滚按钮                  | 无,通过git操作                                  |
| 灰度发布       | 支持                                              | 不支持                                          |
| 授权/审计/审核 | 界面上直接操作，且支持修改和发布权限分离          | 需要通过git仓库设置，且不支持修改和发布权限分离 |
| 实例配置监控   | 可以方便查看哪些客户端使用哪些配置                | 不支持                                          |
| 配置获取性能   | 快，通过数据库访问 + 缓存支持                     | 慢，通过git clone repo，然后本地读取            |
| 客户端支持     | 原生支持Java/.NET，提供API，支持Spring annotation | Spring 应用 + annotation支持                    |



#### 本地快速部署请参见[Quick Start](https://github.com/ctripcorp/apollo/wiki/Quick-Start)



### SpringBoot 集成Apollo客户端 实现从配置中心读取配置信息及更新配置通知实时更新

#### 1、创建maven项目，引入SpringBoot和apollo客户端依赖

```xml
<dependencies>
    <dependency>
      <groupId>com.ctrip.framework.apollo</groupId>
      <artifactId>apollo-client</artifactId>
      <version>1.1.1</version>
    </dependency>

    <dependency>
      <groupId>org.springframework.boot</groupId>
      <artifactId>spring-boot-starter</artifactId>
      <exclusions>
        <exclusion>
          <groupId>org.springframework.boot</groupId>
          <artifactId>spring-boot-starter-logging</artifactId>
        </exclusion>
      </exclusions>
    </dependency>
    <dependency>
      <groupId>org.springframework.boot</groupId>
      <artifactId>spring-boot-starter-web</artifactId>
    </dependency>
    <dependency>
      <groupId>org.springframework.boot</groupId>
      <artifactId>spring-boot-starter-test</artifactId>
    </dependency>
    <dependency>
      <groupId>org.springframework.boot</groupId>
      <artifactId>spring-boot-starter-log4j2</artifactId>
    </dependency>
  </dependencies>
```



#### 2、在portal web中新建项目

![1567576747335](../img/application.png)


#### 3、在spring boot工程中创建application.yml

```yml
server:
  port: 8088

app:
  id: baozun-pac
apollo:
  meta: http://127.0.0.1:8080
  bootstrap:
    enabled: true
    eagerLoad:
      enabled: true
    namespaces: application,notify
```

##### 配置说明

- **app.id 是应用中的身份信息, 是配置中心获取配置的重要信息**
- **apollo.meta 用于封装Eureka的服务发现接口**
- **apollo.bootstrap.enabled 在应用启动阶段,向Spring 容器注入被托管的applicatio.properties文件的配置信息**
- **apollo.bootstrap.eagerLoad.enabled 将Apollo配置加载提到初始化日志系统之前**
- **apollo.bootstrap.namespaces Apollo空间名称,可以多个**

### 4、测试类

```java
package com.springboot.apollo.exercise.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class MetaController {

  @Value("${batchOne:6000}")
  String c;

  @GetMapping("/meta")
  public String meta(String name) {
    log.debug("debug log...");
    log.info("debug info...");
    log.warn("debug warn...");
    return "hi" + name + " , this batchOne" + batchOne;
  }
}

```



### 5、监听Apollo配置更新

```java
package com.springboot.apollo.exercise.config;

import com.ctrip.framework.apollo.model.ConfigChange;
import com.ctrip.framework.apollo.model.ConfigChangeEvent;
import com.ctrip.framework.apollo.spring.annotation.ApolloConfigChangeListener;
import java.util.Set;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ApolloConfig {

  @ApolloConfigChangeListener(value = {"application", "application.yml"})
  public void onChange(ConfigChangeEvent changeEvent) {
    Set<String> strings = changeEvent.changedKeys();
    for (String key : strings) {
      ConfigChange change = changeEvent.getChange(key);
      log.debug("oldValue:{},newValue{}", change.getOldValue(), change.getNewValue());
    }
  }
}

```

### <font color="red">注意:记得在启动程序的入口加上 @SpringBootApplication注解</font>



![1567578082795](../img/web.png)



![1567578105651](../img/postman.png)



#### 到此,Spring boot Apollo 已经配置好了，更详细的Apollo请至https://github.com/ctripcorp/apollo

