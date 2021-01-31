package io.github.gravetii.controller.start;

import io.github.gravetii.controller.FxController;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class FooterController implements FxController {
  private final Stage stage;

  @FXML private Label footer;

  public FooterController(Stage stage) {
    this.stage = stage;
  }

  public void show(String text) {
    footer.setText(text);
  }
}
