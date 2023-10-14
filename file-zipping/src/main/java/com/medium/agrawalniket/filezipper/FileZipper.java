package com.medium.agrawalniket.filezipper;

import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;

@Builder(setterPrefix = "set")
@Getter
public class FileZipper {

  @NonNull
  private String sourceFilePath;
  @NonNull
  private String sourceFileName;
  @NonNull
  private String sourceFileExtension;
  @NonNull
  private String zippedFilePath;
  @NonNull
  private String zippedFileName;
  private boolean deleteSourceAfterZipped;
}
