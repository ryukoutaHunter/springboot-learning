package com.springboot.apollo.exercise;

import com.ctrip.framework.apollo.spring.annotation.EnableApolloConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableApolloConfig
@SpringBootApplication
public class SpringBootApolloExerciseApplication {

  public static void main(String[] args) {
    SpringApplication.run(SpringBootApolloExerciseApplication.class, args);
  }

}
