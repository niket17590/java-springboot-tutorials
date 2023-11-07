package com.medium.agrawalniket.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import com.medium.agrawalniket.service.HeavyMemoryOperationService;

@RestController
public class HeavyMemoryOperationController {

  HeavyMemoryOperationService service;

  @Autowired
  public HeavyMemoryOperationController(HeavyMemoryOperationService service) {
    this.service = service;
  }

  @GetMapping("getMillionRecords")
  public List<String> getMillionRecords() {
    return service.getMillionRecords();
  }
}
