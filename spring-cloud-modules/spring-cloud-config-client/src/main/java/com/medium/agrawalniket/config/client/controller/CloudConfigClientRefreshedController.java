package com.medium.agrawalniket.config.client.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RefreshScope
@RequestMapping("client/config")
public class CloudConfigClientRefreshedController {

  @Value("${cloud.config.greeting}")
  private String greeting;
 
  @GetMapping("getRefreshedConfig")
  public String getRefreshedConfig() {
    return this.greeting;
  }
}
