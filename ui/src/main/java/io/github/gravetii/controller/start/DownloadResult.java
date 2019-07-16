package io.github.gravetii.controller.start;

import io.github.gravetii.gen.Diztl;
import org.apache.commons.io.FilenameUtils;

public class DownloadResult {
  private Diztl.FileMetadata file;

  public DownloadResult(Diztl.FileMetadata file) {
    this.file = file;
  }

  public String getName() {
    return file.getName();
  }

  public String getSize() {
    return FileResult.humanReadableByteCount(file.getSize());
  }

  public String getType() {
    return FilenameUtils.getExtension(file.getName());
  }

  public String getStatus() {
    return "DOWNLOADING...";
  }

  public boolean empty() {
    return file == null;
  }
}
