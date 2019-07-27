package io.github.gravetii.scene.start;

import io.github.gravetii.controller.start.LogController;
import io.github.gravetii.scene.FxComponent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class LogComponent extends FxComponent<LogController, AnchorPane> {
  private Stage stage;

  protected LogComponent(Stage stage) throws Exception {
    super("log.fxml");
    this.stage = stage;
    create();
  }

  @Override
  protected LogController createController() {
    return new LogController(stage);
  }

  @Override
  protected AnchorPane createNode() throws Exception {
    return loadNode();
  }
}
