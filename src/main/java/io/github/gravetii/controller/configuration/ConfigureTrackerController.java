package io.github.gravetii.controller.configuration;

import io.github.gravetii.client.DiztlClient;
import io.github.gravetii.controller.FxController;
import io.github.gravetii.scene.start.StartScene;
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
  private final StartScene scene;

  @FXML private TextField addrBox;

  private String initialValue;

  public ConfigureTrackerController(DiztlClient client, DBService dbService, StartScene scene) {
    this.client = client;
    this.dbService = dbService;
    this.scene = scene;
  }

  /**
   * Register with the tracker.
   */
  private void register() {
    String tracker = dbService.getTrackerAddress();
    try {
      client.register();
      scene.writeToLog("Connected to tracker at " + "[" + tracker + "]");
    } catch (Exception e) {
      scene.writeToLog("Unable to connect to tracker at " + tracker);
      logger.error("Error while connecting to tracker {}", tracker, e);
    }
  }

  @FXML
  private void initialize() {
    this.initialValue = dbService.getTrackerAddress();
    addrBox.setText(initialValue);
  }

  @FXML
  public void ok() {
    String tracker = addrBox.getText();
    if (StringUtils.isNotEmpty(tracker)) {
      // save the tracker address in db but reconnect only if it has changed.
      dbService.saveTrackerAddress(tracker);
      if (!initialValue.equals(tracker)) {
        scene.writeToLog("Updated tracker address - " + tracker + ". Attempting to connect...");
        this.register();
      }
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
