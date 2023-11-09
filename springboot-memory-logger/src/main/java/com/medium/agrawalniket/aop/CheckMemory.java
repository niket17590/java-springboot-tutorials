package com.medium.agrawalniket.aop;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface CheckMemory {

  long minRequiredMemory() default 0;

  MemoryUnit jvmMemoryUnit() default MemoryUnit.MB;

  enum MemoryUnit {
    KB, MB
  }
}
