package com.medium.agrawalniket.externalsort;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ExternalSortMainApplication implements ApplicationRunner {

  @Value("${input.csv.path}")
  String inputFilePath;

  @Value("${external.sort.temp.folder.path}")
  String tempFileFolder;

  @Value("${external.sort.max.memory}")
  long maxMemory;

  public static void main(String[] args) {
    SpringApplication.run(ExternalSortMainApplication.class, args);
  }

  @Override
  public void run(ApplicationArguments args) throws Exception {
    ExternalMergeSorter.mergeSort(inputFilePath, tempFileFolder, maxMemory);
  }
}
