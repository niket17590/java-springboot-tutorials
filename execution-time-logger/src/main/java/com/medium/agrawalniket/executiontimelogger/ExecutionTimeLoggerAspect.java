package com.medium.agrawalniket.executiontimelogger;


import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import com.medium.agrawalniket.executiontimelogger.ExecutionTimeLogger.ExecutionTimeUnit;


@Aspect
@Component
public class ExecutionTimeLoggerAspect {
  static final Logger logger = LoggerFactory.getLogger(ExecutionTimeLoggerAspect.class);

  @Around("@annotation(executionTimeLogger)")
  public Object executionTimeLogger(ProceedingJoinPoint joinPoint,
      ExecutionTimeLogger executionTimeLogger) {

    try {

      long startTime = System.currentTimeMillis();
      Object proceed = joinPoint.proceed();
      long executionTime = (System.currentTimeMillis() - startTime);
      ExecutionTimeUnit executionTimeUnit = executionTimeLogger.executionTimeUnit();

      switch (executionTimeUnit) {
        case MILLISECONDS:
          break;
        case MINUTES:
          executionTime = executionTime / 60000;
          break;
        case SECONDS:
        default:
          executionTime = executionTime / 1000;
          break;
      }

      int maxThresholdTime = executionTimeLogger.maxThresholdTime();

      if (maxThresholdTime > 0 && executionTime > maxThresholdTime) {
        logger.warn(
            "{} method was executed in {} {} which was higher than expected Max Threshold Time of {} {}",
            joinPoint.getSignature(), executionTime, executionTimeUnit, maxThresholdTime,
            executionTimeUnit);
        // Trigger an Email
        // Log it in Database
      } else {
        logger.info("{} method was executed in {} {}", joinPoint.getSignature(), executionTime,
            executionTimeUnit);
      }
      return proceed;
    } catch (Throwable e) {
      logger.error("There was an error while calculating method execution time for {}",
          joinPoint.getSignature(), e);
      return null;
    }
  }
}
