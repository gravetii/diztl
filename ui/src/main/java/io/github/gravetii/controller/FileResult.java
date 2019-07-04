package io.github.gravetii.controller;

import io.github.gravetii.gen.Diztl;

public class FileResult {
  private Diztl.FileMetadata file;
  private Diztl.Node source;

  public FileResult(Diztl.FileMetadata file, Diztl.Node source) {
    this.file = file;
    this.source = source;
  }

  private static String humanReadableByteCount(long bytes) {
    int unit = 1000;
    if (bytes < unit) return bytes + " B";
    int exp = (int) (Math.log(bytes) / Math.log(unit));
    char pre = "kMGTPE".charAt(exp-1);
    return String.format("%.1f %sB", bytes / Math.pow(unit, exp), pre);
  }

  public String getFileName() {
    return file.getName();
  }

  public String getFileSize() {
    return FileResult.humanReadableByteCount(file.getSize());
  }

  public Diztl.FileMetadata getFile() {
    return file;
  }

  public void setFile(Diztl.FileMetadata file) {
    this.file = file;
  }

  public Diztl.Node getSource() {
    return source;
  }

  public void setSource(Diztl.Node source) {
    this.source = source;
  }

  public boolean empty() {
    return file == null;
  }
}
