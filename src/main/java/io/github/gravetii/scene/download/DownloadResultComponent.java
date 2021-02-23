package io.github.gravetii.scene.download;

import io.github.gravetii.controller.download.DownloadResultController;
import io.github.gravetii.scene.FxComponent;
import javafx.scene.layout.AnchorPane;

public class DownloadResultComponent extends FxComponent<DownloadResultController, AnchorPane> {

  public DownloadResultComponent() {
    super("downloadResult.fxml");
    create();
  }

  @Override
  protected DownloadResultController createController() {
    return new DownloadResultController();
  }

  @Override
  protected AnchorPane createNode() {
    return this.loadNode();
  }
}
