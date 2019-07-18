package io.github.gravetii.client.handler;

import io.github.gravetii.gen.Diztl;
import io.github.gravetii.util.Utils;
import javafx.concurrent.Task;
import org.apache.commons.io.FilenameUtils;

public class DownloadResult extends Task<Void> {
  private Diztl.FileMetadata file;
  private int chunks;

  public DownloadResult(Diztl.FileMetadata file) {
    this.file = file;
  }

  public void setChunks(int chunks) {
    this.chunks = chunks;
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

  public void update(int chunk) {
    updateProgress(chunk, chunks);
  }

  public void close() {
    updateMessage("Completed");
  }

  @Override
  protected Void call() {
    updateProgress(0, 0);
    updateMessage("Connecting...");
    return null;
  }
}
