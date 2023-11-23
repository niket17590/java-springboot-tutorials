package com.medium.agrawalniket.producer.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/producer")
public class ProducerServiceController {

  @GetMapping("getGreeting/{userName}")
  public String getGreeting(@PathVariable("userName") String userName) {
    
    return "Greeting sent from Producer for "+userName;
  }
}
