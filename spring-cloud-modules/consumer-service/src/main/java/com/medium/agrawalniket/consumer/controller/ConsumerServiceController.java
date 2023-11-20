package com.medium.agrawalniket.consumer.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/consumer")
public class ConsumerServiceController {

  @Value("${consumer.greeting}")
  private String greeting;
  
  @GetMapping("getGreeting")
  public String getGreeting() {
    return this.greeting;
  }
}
