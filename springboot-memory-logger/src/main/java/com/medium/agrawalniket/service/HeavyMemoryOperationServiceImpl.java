package com.medium.agrawalniket.service;

import org.springframework.stereotype.Service;
import com.medium.agrawalniket.aop.CheckMemory;
import com.medium.agrawalniket.aop.CheckMemory.MemoryUnit;

@Service
public class HeavyMemoryOperationServiceImpl implements HeavyMemoryOperationService {

  @Override
  @CheckMemory(jvmMemoryUnit = MemoryUnit.MB, minRequiredMemory = 512)
  public void processMillionRecords() {
    // Big Memory Operations
  }
}
