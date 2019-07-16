package io.github.gravetii.controller.start;

import io.github.gravetii.controller.FxController;
import javafx.fxml.FXML;
import javafx.scene.control.ProgressBar;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class DownloadProgressController implements FxController {
  private static final ExecutorService EXECUTOR =
          Executors.newSingleThreadExecutor(
                  (r) -> {
                    Thread thread = new Thread(r);
                    thread.setDaemon(true);
                    return thread;
                  });

  @FXML private ProgressBar progressBar;

  public ProgressBar getProgressBar() {
    return this.progressBar;
  }
}
