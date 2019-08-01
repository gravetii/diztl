package io.github.gravetii.controller.start;

import io.github.gravetii.gen.Diztl;
import io.github.gravetii.util.Utils;
import org.apache.commons.io.FilenameUtils;

public class FileResult {
  private Diztl.FileMetadata file;
  private Diztl.Node source;

  public FileResult(Diztl.FileMetadata file, Diztl.Node source) {
    this.file = file;
    this.source = source;
  }

  public String getName() {
    return file.getName();
  }

  public String getSize() {
    return Utils.humanReadableByteCount(file.getSize());
  }

  public String getType() {
    return FilenameUtils.getExtension(file.getName());
  }

  public String getPath() {
    return file.getDir();
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

  @Override
  public String toString() {
    return "FileResult{" + "file=" + file + ", source=" + source + '}';
  }
}
