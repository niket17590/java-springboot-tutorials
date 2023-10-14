package com.medium.agrawalniket.executiontimelogger;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class ExecutionTimeLoggerApplicationTest {

  @Autowired
  MethodExecutions capturingMethods;

  @Test
  public void testMethod() throws InterruptedException {
    capturingMethods.aspectMethodOne();
    capturingMethods.aspectMethodTwo();
    capturingMethods.aspectMethodThree();

  }

}
