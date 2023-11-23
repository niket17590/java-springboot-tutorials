package com.medium.agrawalniket.consumer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import com.medium.agrawalniket.consumer.feign.ConsumerFeignClient;

@RestController
public class ConsumerServiceController {

  @Autowired
  RestTemplate restTemplate;

  @Autowired
  ConsumerFeignClient feignClient;

  @GetMapping("getGreeting/{userName}")
  public String getGreeting(@PathVariable("userName") String userName) {
    return restTemplate.getForEntity("http://PRODUCER-SERVICE/producer/getGreeting/{userName}",
        String.class, userName).getBody();
  }

  @GetMapping("getFeignGreeting/{userName}")
  public String getFeignGreeting(@PathVariable("userName") String userName) {
    return feignClient.getGreeting(userName);

  }
}
