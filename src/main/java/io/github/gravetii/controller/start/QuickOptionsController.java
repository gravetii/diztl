package io.github.gravetii.controller.start;

import io.github.gravetii.controller.FxController;
import io.github.gravetii.scene.configuration.ConfigureTrackerScene;
import io.github.gravetii.scene.start.StartScene;
import io.github.gravetii.scene.userdir.UserDirsScene;
import io.github.gravetii.store.DBService;
import io.github.gravetii.util.Utils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Tooltip;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class QuickOptionsController implements FxController {

  private static final Logger logger =
      LoggerFactory.getLogger(QuickOptionsController.class.getCanonicalName());

  private final DBService dbService;
  private final StartScene scene;

  @FXML private Button configureDirsBtn;
  @FXML private Button configureTrackerBtn;

  public QuickOptionsController(DBService dbService, StartScene scene) {
    this.dbService = dbService;
    this.scene = scene;
  }

  @FXML
  private void initialize() {
    configureDirsBtn.setTooltip(new Tooltip("Configure your share and download folders"));
    configureTrackerBtn.setTooltip(new Tooltip("Configure tracker"));
  }

  private Stage newModalWindow() {
    Stage stage = new Stage();
    stage.initOwner(scene.getWindow());
    stage.setResizable(false);
    stage.initModality(Modality.APPLICATION_MODAL);
    return stage;
  }

  @FXML
  public void configureDirs(ActionEvent e) {
    Stage stage = newModalWindow();
    UserDirsScene scene = new UserDirsScene(dbService);
    Utils.display(stage, scene);
  }

  @FXML
  public void configureTracker(ActionEvent e) {
    Stage stage = newModalWindow();
    ConfigureTrackerScene scene = new ConfigureTrackerScene(dbService);
    Utils.display(stage, scene);
  }
}
