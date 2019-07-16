package io.github.gravetii.client.handler;

import javafx.concurrent.Task;

public class DownloadProgressTask extends Task<Void> {
  private int chunks;

  public DownloadProgressTask() {
    updateProgress(0, 0);
  }

  public void setChunks(int chunks) {
    this.chunks = chunks;
    updateProgress(0, chunks);
  }

  public void update(int chunk) {
    updateProgress(chunk, chunks);
  }

  @Override
  protected Void call() throws Exception {
    return null;
  }
}
