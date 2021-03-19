package com.davidli.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class ApplicationLoader implements CommandLineRunner {
  @Autowired SparkService SparkService;

  @Override
  public void run(String... strings) throws Exception {
    SparkService.impactCalculation();
  }
}
