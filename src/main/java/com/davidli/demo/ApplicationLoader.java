package com.davidli.demo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class ApplicationLoader implements CommandLineRunner {
  @Autowired SparkService SparkService;

  @Override
  public void run(String... strings) throws Exception {
    SparkService.impactCalculation();
    log.info("run success");
  }
}
