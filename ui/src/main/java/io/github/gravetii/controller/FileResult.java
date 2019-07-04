package io.github.gravetii.controller;

import io.github.gravetii.gen.Diztl;

public class FileResult {
  private Diztl.FileMetadata file;
  private Diztl.Node source;

  public FileResult(Diztl.FileMetadata file, Diztl.Node source) {
    this.file = file;
    this.source = source;
  }

  public String getFileName() {
    return file.getName();
  }

  public Long getFileSize() {
    return file.getSize();
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
