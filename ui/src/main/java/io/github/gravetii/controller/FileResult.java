package io.github.gravetii.controller;

public class FileResult {
  private String fileName;
  private Long fileSize;

  public FileResult(String fileName, Long fileSize) {
    this.fileName = fileName;
    this.fileSize = fileSize;
  }

  public void setFileName(String fileName) {
    this.fileName = fileName;
  }

  public void setFileSize(Long fileSize) {
    this.fileSize = fileSize;
  }

  public String getFileName() {
    return fileName;
  }

  public Long getFileSize() {
    return fileSize;
  }

  public boolean empty() {
    return fileName == null;
  }
}
