package io.github.gravetii.controller.start;

import io.github.gravetii.controller.FxController;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Separator;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

public class LogController implements FxController {
  private Stage stage;

  @FXML private TextArea logArea;
  @FXML private Separator separator;

  public LogController(Stage stage) {
    this.stage = stage;
  }

  public void writeToLog(String text) {
    Platform.runLater(() -> logArea.appendText(text + "\n"));
  }
}
