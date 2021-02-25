package io.github.gravetii.scene.start;

import io.github.gravetii.controller.start.LogController;
import io.github.gravetii.scene.FxComponent;
import javafx.scene.layout.AnchorPane;

public class LogComponent extends FxComponent<LogController, AnchorPane> {
  protected LogComponent() {
    super("log.fxml");
    create();
  }

  @Override
  protected LogController createController() {
    return new LogController();
  }

  @Override
  protected AnchorPane createNode() {
    return loadNode();
  }
}
