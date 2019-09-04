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
