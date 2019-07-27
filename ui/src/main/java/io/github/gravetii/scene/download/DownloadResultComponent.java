package io.github.gravetii.scene.download;

import io.github.gravetii.controller.download.DownloadResultController;
import io.github.gravetii.scene.FxComponent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class DownloadResultComponent extends FxComponent<DownloadResultController, AnchorPane> {
  private Stage stage;

  public DownloadResultComponent(Stage stage) {
    super("downloadResult.fxml");
    this.stage = stage;
    this.create();
  }

  @Override
  protected DownloadResultController createController() {
    return new DownloadResultController(stage);
  }

  @Override
  protected AnchorPane createNode() {
    return this.loadNode();
  }
}
