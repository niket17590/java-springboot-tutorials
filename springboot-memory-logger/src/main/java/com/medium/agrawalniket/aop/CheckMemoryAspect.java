package com.medium.agrawalniket.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import com.medium.agrawalniket.aop.CheckMemory.MemoryUnit;
import com.medium.agrawalniket.exception.LowMemoryException;

@Aspect
@Component
public class CheckMemoryAspect {

  static final Logger logger = LoggerFactory.getLogger(CheckMemoryAspect.class);

  @Before("@annotation(checkMemory)")
  public void executionTimeLogger(JoinPoint joinPoint, CheckMemory checkMemory) {
    long freeMemory = getFreeMemory(checkMemory.jvmMemoryUnit());
    long thresholdMemory = checkMemory.maxThresholdMemory();
    if (thresholdMemory != 0 && thresholdMemory > freeMemory) {
      logger.warn("Checking JVM memory before calling method: {}", joinPoint.getSignature());
      logger.warn("Free memory in JVM is {}{} and it is lower than defined threshold memory: {}{} ",
          freeMemory, checkMemory.jvmMemoryUnit(), thresholdMemory, checkMemory.jvmMemoryUnit());
      // Raise Email Alert
      // Raise Exception
      throw new LowMemoryException("JVM Memory is Low hence suspending operation");
    }
  }

  private long getFreeMemory(MemoryUnit memoryUnit) {
    return getMemory(Runtime.getRuntime().freeMemory(), memoryUnit);
  }

  private long getMemory(long memorySize, MemoryUnit memoryUnit) {
    int byteToMb = 1024 * 1024;
    int byteToKb = 1024;

    switch (memoryUnit) {
      case KB:
        return memorySize / byteToKb;
      case MB:
        return memorySize / byteToMb;
      default:
        return memorySize;
    }
  }
}
