package com.medium.agrawalniket.filezipper;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FileZippingApplication {

  static Logger logger = LoggerFactory.getLogger(FileZippingApplication.class);

  public static void main(String[] args) {

    FileZipper fileZipper =
        FileZipper.builder().setSourceFilePath("C:/Data/").setSourceFileName("UserData")
            .setSourceFileExtension("xlsx").setZippedFileName("ZippedUserData")
            .setZippedFilePath("C:/Data/").setDeleteSourceAfterZipped(true).build();

    FileZippingService.zipExportFile(fileZipper);

    if (FileUtils.getFile("C:/Data/ZippedUserData.zip").exists()) {
      logger.info("File Zipped Successfully");
    }

  }

}
