package com.medium.agrawalniket.externalsort;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.apache.commons.lang3.time.StopWatch;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.google.code.externalsorting.csv.CsvExternalSort;
import com.google.code.externalsorting.csv.CsvSortOptions;
import com.google.common.collect.Lists;

public class ExternalMergeSorter {

  static final Logger logger = LoggerFactory.getLogger(ExternalMergeSorter.class);

  public static void mergeSort(String inputFile, String tempFileFolder, long maxMemory) {

    StopWatch stopwatch = StopWatch.createStarted();
    try {
      logger.info("Starting External Merge Sort for : {}", inputFile);
      String outputFile = inputFile.split(".csv")[0].concat("_Sorted.csv");
      Files.deleteIfExists(Paths.get(outputFile));
      Files.createDirectories(Paths.get(tempFileFolder));

      Comparator<CSVRecord> comparator =
          Comparator.comparing(op -> op.get(0).concat("|").concat(op.get(1)));

      CsvSortOptions sortOptions =
          new CsvSortOptions.Builder(comparator, CsvExternalSort.DEFAULTMAXTEMPFILES,
              1024 * 1024 * maxMemory).charset(Charset.defaultCharset()).distinct(false)
                  .numHeader(1).skipHeader(false).format(CSVFormat.DEFAULT).build();

      ArrayList<CSVRecord> header = Lists.newArrayList();
      List<File> sortInBatch = CsvExternalSort.sortInBatch(new File(inputFile),
          new File(tempFileFolder), sortOptions, header);
      CsvExternalSort.mergeSortedFiles(sortInBatch, new File(outputFile), sortOptions, true,
          header);
   
    } catch (IOException | ClassNotFoundException e) {
      logger.error("Error while External Merge Sort operation");
    }
    logger.info("Total Time consumed for External Merge Sort : {} seconds.",
        stopwatch.getTime(TimeUnit.SECONDS));
  }
}
