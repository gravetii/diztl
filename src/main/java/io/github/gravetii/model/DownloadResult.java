package io.github.gravetii.model;

import io.github.gravetii.grpc.Diztl;
import io.github.gravetii.util.Utils;
import javafx.concurrent.Task;
import org.apache.commons.io.FilenameUtils;

public class DownloadResult extends Task<Void> {
  private final Diztl.FileMetadata file;
  private final String path;
  private final String filepath;

  private int chunks;

  public DownloadResult(Diztl.FileMetadata file, String path) {
    this.file = file;
    this.path = path;
    this.filepath = Utils.getFilePath(file).toString();
  }

  public void first(Diztl.FileChunk f) {
    chunks = f.getChunks();
    updateMessage("Downloading...");
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
    return path;
  }

  public String getFilepath() {
    return filepath;
  }

  public void onNext(Diztl.FileChunk chunk) {
    updateProgress(chunk.getChunk(), chunks);
  }

  public void onError(Throwable t) {
    updateMessage("Failed");
  }

  public void onComplete() {
    updateMessage("Completed");
  }

  public boolean isDone() {
    String msg = getMessage();
    return "Completed".equals(msg);
  }

  @Override
  protected Void call() {
    updateProgress(0, 0);
    updateMessage("Connecting...");
    return null;
  }
}