package io.github.gravetii.scene.start;

import io.github.gravetii.controller.start.LogController;
import io.github.gravetii.scene.FxComponent;
import javafx.scene.layout.AnchorPane;

public class LogComponent extends FxComponent<LogController, AnchorPane> {
  private boolean errorLog;

  protected LogComponent(boolean errorLog) {
    super("log.fxml");
    this.errorLog = errorLog;
    create();
  }

  @Override
  protected LogController createController() {
    return new LogController(errorLog);
  }

  @Override
  protected AnchorPane createNode() {
    return loadNode();
  }
}
