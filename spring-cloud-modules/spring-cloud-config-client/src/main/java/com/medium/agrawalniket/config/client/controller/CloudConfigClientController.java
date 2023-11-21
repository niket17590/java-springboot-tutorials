package com.medium.agrawalniket.config.client.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.medium.agrawalniket.config.client.configuration.CloudConfigClientConfig;

@RestController
@RequestMapping("client/config")
public class CloudConfigClientController {

  @Autowired
  CloudConfigClientConfig config;

  @Value("${cloud.config.greeting}")
  private String greeting;

  @GetMapping("getConfigGreeting")
  public String getConfigGreeting() {
    return config.getGreeting();
  }

  @GetMapping("getValueGreeting")
  public String getValueGreeting() {
    return this.greeting;
  }

}
