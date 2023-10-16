package com.medium.agrawalniket.executiontimelogger.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import com.medium.agrawalniket.executiontimelogger.MethodExecutions;

@RestController
public class ExecutionTimeLoggerController {

  @Autowired
  MethodExecutions capturingMethods;

  @GetMapping(value = "/logExecuteTime")
  public String logExecuteTime() throws InterruptedException {
    capturingMethods.aspectMethodOne();
    capturingMethods.aspectMethodTwo();
    capturingMethods.aspectMethodThree();
    return "Request completed successfully";
  }
}
