package io.github.gravetii.controller.configuration;

import io.github.gravetii.controller.FxController;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.Window;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ConfigureTrackerController implements FxController {

  private static final Logger logger =
      LoggerFactory.getLogger(ConfigureTrackerController.class.getCanonicalName());

  @FXML private TextField ipBox;

  public ConfigureTrackerController() {
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
    logger.info("Updated tracker config...");
    // todo update ip in config here...
    close();
  }

  @FXML
  public void cancel() {
    close();
  }

  private void close() {
    Window window = ipBox.getScene().getWindow();
    if (window instanceof Stage) {
      Stage stage = (Stage) window;
      stage.close();
    }
  }
}
