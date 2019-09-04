package com.springboot.apollo.exercise.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class MetaController {

  @Value("${batchOne:6000}")
  String batchOne;

  @GetMapping("/meta")
  public String meta(String name) {
    log.debug("debug log...");
    log.info("debug info...");
    log.warn("debug warn...");
    return "hi" + name + " , this batchOne" + batchOne;
  }
}
