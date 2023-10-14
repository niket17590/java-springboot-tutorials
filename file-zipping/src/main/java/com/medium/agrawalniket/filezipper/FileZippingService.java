package com.medium.agrawalniket.filezipper;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FileZippingService {

  private static final String DOT = ".";
  private static final String ZIP = ".zip";

  private FileZippingService() {}

  static Logger logger = LoggerFactory.getLogger(FileZippingService.class);

  /**
   * Method to Zip a file
   * 
   * @param fileZipper
   */
  public static void zipExportFile(FileZipper fileZipper) {

    String sourceFileWithPath =
        fileZipper.getSourceFilePath().concat(fileZipper.getSourceFileName()).concat(DOT)
            .concat(fileZipper.getSourceFileExtension());
    String zippedFileWithPath =
        fileZipper.getZippedFilePath().concat(fileZipper.getZippedFileName()).concat(ZIP);
    logger.info("Source File : {} will be zipped into {}", sourceFileWithPath, zippedFileWithPath);
    try {
      File fileToZip = new File(sourceFileWithPath);
      if (fileToZip.exists()) {
        FileOutputStream fos = new FileOutputStream(zippedFileWithPath);
        ZipOutputStream zipOut = new ZipOutputStream(fos);
        FileInputStream fis = new FileInputStream(fileToZip);
        ZipEntry zipEntry = new ZipEntry(fileToZip.getName());
        zipOut.putNextEntry(zipEntry);
        byte[] bytes = new byte[1024];
        int length;
        while ((length = fis.read(bytes)) >= 0) {
          zipOut.write(bytes, 0, length);
        }
        zipOut.close();
        fis.close();
        fos.close();
        logger.info("{} zipped successfully at {}", sourceFileWithPath, zippedFileWithPath);
        if (fileZipper.isDeleteSourceAfterZipped())
          deleteFile(sourceFileWithPath);
      }
    } catch (IOException e) {
      logger.error("Error while Zipping the file", e);
      deleteFile(zippedFileWithPath);
    }

  }

  /**
   * Method to delete a file at given path
   * 
   * @param fileToDelete
   */
  private static void deleteFile(String fileToDelete) {
    File filetoDelete = new File(fileToDelete);
    if (filetoDelete.exists()) {
      if (FileUtils.deleteQuietly(filetoDelete)) {
        logger.info("{} deleted successfully.", fileToDelete);
      } else {
        logger.info("{} deletion unsuccessfull.", fileToDelete);
      }
    } else {
      logger.info("{} was not found for deletion.", fileToDelete);
    }

  }

}
