package com.medium.agrawalniket.executiontimelogger;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ExecutionTimeLogger {

  int maxThresholdTime() default 0;

  ExecutionTimeUnit executionTimeUnit() default ExecutionTimeUnit.SECONDS;

  enum ExecutionTimeUnit {
    MILLISECONDS, SECONDS, MINUTES
  }
}
