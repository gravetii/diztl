package io.github.gravetii.controller.configuration;

import io.github.gravetii.client.DiztlClient;
import io.github.gravetii.controller.FxController;
import io.github.gravetii.store.DBService;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.Window;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ConfigureTrackerController implements FxController {

  private static final Logger logger =
      LoggerFactory.getLogger(ConfigureTrackerController.class.getCanonicalName());

  private final DiztlClient client;
  private final DBService dbService;

  @FXML private TextField addrBox;

  private String initialValue;

  public ConfigureTrackerController(DiztlClient client, DBService dbService) {
    this.client = client;
    this.dbService = dbService;
  }

  @FXML
  private void initialize() {
    this.initialValue = dbService.getTrackerAddress();
    addrBox.setText(initialValue);
  }

  @FXML
  public void ok() {
    String tracker = addrBox.getText();
    if (StringUtils.isNotEmpty(tracker) && !this.initialValue.equals(tracker)) {
      dbService.saveTrackerAddress(tracker);
    }

    close();
  }

  @FXML
  public void cancel() {
    close();
  }

  private void close() {
    Window window = addrBox.getScene().getWindow();
    if (window instanceof Stage) {
      Stage stage = (Stage) window;
      stage.close();
    }
  }
}
