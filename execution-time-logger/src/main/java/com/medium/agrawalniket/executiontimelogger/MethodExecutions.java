package com.medium.agrawalniket.executiontimelogger;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;
import com.medium.agrawalniket.executiontimelogger.ExecutionTimeLogger.ExecutionTimeUnit;


@Component
public class MethodExecutions {

  static final Logger logger = LoggerFactory.getLogger(MethodExecutions.class);

  public void traditionalApproachOne() throws InterruptedException {
    logger.info("Calling method traditionalApproachOne");
    long startTime = System.currentTimeMillis();
    Thread.sleep(2000);
    long endTime = System.currentTimeMillis();

    logger.info("Method TraditionalApproachOne took {} seconds", (endTime - startTime) / 1000);

  }

  public void traditionalApproachOTwo() throws InterruptedException {
    logger.info("Calling method traditionalApproachTwo");
    StopWatch watch = new StopWatch();
    watch.start();
    Thread.sleep(2000);
    watch.stop();
    logger.info("Method TraditionalApproachOne took {} seconds", watch.getTotalTimeSeconds());
  }

  @ExecutionTimeLogger
  public void aspectMethodOne() throws InterruptedException {
    Thread.sleep(2000);
  }

  @ExecutionTimeLogger(maxThresholdTime = 2)
  public void aspectMethodTwo() throws InterruptedException {
    Thread.sleep(3000);
  }

  @ExecutionTimeLogger(maxThresholdTime = 50, executionTimeUnit = ExecutionTimeUnit.MILLISECONDS)
  public void aspectMethodThree() throws InterruptedException {
    Thread.sleep(100);
  }
}
