package io.github.gravetii.controller.configuration;

import io.github.gravetii.controller.FxController;
import io.github.gravetii.scene.start.StartScene;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ConfigureTrackerController implements FxController {

  private static final Logger logger =
      LoggerFactory.getLogger(ConfigureTrackerController.class.getCanonicalName());

  private final Stage stage;
  private final StartScene scene;

  @FXML private TextField ipBox;

  public ConfigureTrackerController(Stage stage, StartScene scene) {
    this.stage = stage;
    this.scene = scene;
  }

  @FXML
  private void initialize() {
    // todo
    String tracker = "127.0.0.1:50036";
    ipBox.setText(tracker);
  }

  @FXML
  public void ok() {
    String ip = ipBox.getText();
    // todo update ip in config here...
    logger.info("Updated tracker config...");
    stage.close();
  }

  @FXML
  public void cancel() {
    stage.close();
  }
}
