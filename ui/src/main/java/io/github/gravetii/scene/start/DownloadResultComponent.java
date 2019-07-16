package io.github.gravetii.scene.start;

import io.github.gravetii.controller.start.DownloadResultController;
import io.github.gravetii.scene.FxComponent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class DownloadResultComponent extends FxComponent<DownloadResultController, AnchorPane> {
  private Stage stage;

  protected DownloadResultComponent(Stage stage) throws Exception {
    super("downloadResult.fxml");
    this.stage = stage;
    this.create();
  }

  @Override
  protected DownloadResultController createController() {
    return new DownloadResultController(stage);
  }

  @Override
  protected AnchorPane createNode() throws Exception {
    return this.loadNode();
  }
}
