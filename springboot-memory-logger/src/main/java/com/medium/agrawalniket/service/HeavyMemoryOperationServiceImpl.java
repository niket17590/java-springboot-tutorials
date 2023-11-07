package com.medium.agrawalniket.service;

import java.util.List;
import org.springframework.stereotype.Service;
import com.google.common.collect.Lists;
import com.medium.agrawalniket.aop.CheckMemory;
import com.medium.agrawalniket.aop.CheckMemory.MemoryUnit;

@Service
public class HeavyMemoryOperationServiceImpl implements HeavyMemoryOperationService {

  @Override
  @CheckMemory(jvmMemoryUnit = MemoryUnit.MB,maxThresholdMemory = 256)
  public List<String> getMillionRecords() {
    // Big Memory Operations
    List<String> bigDataList = Lists.newArrayList();
    return bigDataList;
  }

  
}
