package io.github.gravetii.controller.start;

import io.github.gravetii.gen.Diztl;
import org.apache.commons.io.FilenameUtils;

public class FileResult {
  private Diztl.FileMetadata file;
  private Diztl.Node source;

  public FileResult(Diztl.FileMetadata file, Diztl.Node source) {
    this.file = file;
    this.source = source;
  }

  public static String humanReadableByteCount(long bytes) {
    int unit = 1000;
    if (bytes < unit) return bytes + " B";
    int exp = (int) (Math.log(bytes) / Math.log(unit));
    char pre = "kMGTPE".charAt(exp - 1);
    return String.format("%.1f %sB", bytes / Math.pow(unit, exp), pre);
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

  public String getPath() {
    return file.getPath();
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
