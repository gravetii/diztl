package io.github.gravetii.scene.start;

import io.github.gravetii.controller.start.DownloadProgressController;
import io.github.gravetii.scene.FxComponent;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.AnchorPane;

public class DownloadProgressComponent extends FxComponent<DownloadProgressController, AnchorPane> {
  DownloadProgressComponent() throws Exception {
    super("downloadProgress.fxml");
    this.create();
  }

  @Override
  protected DownloadProgressController createController() {
    return new DownloadProgressController();
  }

  @Override
  protected AnchorPane createNode() throws Exception {
    return this.loadNode();
  }

  public ProgressBar getProgressBar() {
    return this.getController().getProgressBar();
  }
}
