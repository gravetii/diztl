package io.github.gravetii.client.handler;

import io.github.gravetii.gen.Diztl;
import io.github.gravetii.util.Utils;
import javafx.concurrent.Task;
import org.apache.commons.io.FilenameUtils;

public class DownloadResult extends Task<Void> {
  private Diztl.FileMetadata file;
  private String path;
  private int chunks;
  private String filepath;

  public DownloadResult(Diztl.FileMetadata file, String path) {
    this.file = file;
    this.path = path;
    this.filepath = Utils.getFullFilePath(file);
  }

  public void first(Diztl.DownloadChunk f) {
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

  public void next(int chunk) {
    updateProgress(chunk, chunks);
  }

  public void error(Throwable t) {
    updateMessage("Failed");
  }

  public void completed() {
    updateMessage("Completed");
  }

  @Override
  protected Void call() {
    updateProgress(0, 0);
    updateMessage("Connecting...");
    return null;
  }
}
