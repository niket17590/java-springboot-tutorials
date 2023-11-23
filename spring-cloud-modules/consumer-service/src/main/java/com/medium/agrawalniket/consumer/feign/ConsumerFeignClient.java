package com.medium.agrawalniket.consumer.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("PRODUCER-SERVICE")
public interface ConsumerFeignClient {

  @GetMapping("producer/getGreeting/{userName}")
  String getGreeting(@PathVariable("userName") String userName);
}
