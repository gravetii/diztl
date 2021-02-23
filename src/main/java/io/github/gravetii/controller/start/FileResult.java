package io.github.gravetii.controller.start;

import io.github.gravetii.grpc.Diztl;
import io.github.gravetii.grpc.FileMetadata;
import io.github.gravetii.grpc.Node;
import io.github.gravetii.util.Utils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;

public class FileResult {
  private FileMetadata file;
  private Node source;

  public FileResult(FileMetadata file, Node source) {
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
    return StringUtils.defaultIfEmpty(FilenameUtils.getExtension(file.getName()), "unknown");
  }

  public String getPath() {
    return file.getDir();
  }

  public FileMetadata getFile() {
    return file;
  }

  public void setFile(FileMetadata file) {
    this.file = file;
  }

  public Node getSource() {
    return source;
  }

  public void setSource(Node source) {
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
