package com.medium.agrawalniket.controller;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import com.medium.agrawalniket.service.HeavyMemoryOperationService;

@RestController
public class HeavyMemoryOperationController {

  HeavyMemoryOperationService service;

  @Autowired
  public HeavyMemoryOperationController(HeavyMemoryOperationService service) {
    this.service = service;
  }

  @PostMapping("processMillionRecords")
  public void processtMillionRecords() {
    // triggering a method to occupy some memory
    List<String> dummyData = IntStream.range(0, 1000000).boxed()
        .map(num -> RandomStringUtils.random(50)).collect(Collectors.toList());
    service.processMillionRecords();
  }
}
