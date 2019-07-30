package io.github.gravetii.controller.start;

import io.github.gravetii.controller.FxController;
import io.github.gravetii.scene.configuration.ConfigureTrackerScene;
import io.github.gravetii.scene.start.StartScene;
import io.github.gravetii.scene.userdir.UserDirsScene;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class QuickOptionsController implements FxController {
  private static final Logger logger =
      LoggerFactory.getLogger(QuickOptionsController.class.getCanonicalName());

  private StartScene parent;

  @FXML private Button configureDirsBtn;
  @FXML private Button configureTrackerBtn;

  public QuickOptionsController(StartScene parent) {
    this.parent = parent;
  }

  private Stage newModalWindow() {
    Stage stage = new Stage();
    stage.setResizable(false);
    stage.initModality(Modality.APPLICATION_MODAL);
    return stage;
  }

  @FXML
  private void configureDirs(ActionEvent event) {
    Stage stage = newModalWindow();
    UserDirsScene scene = new UserDirsScene(stage, parent);
    scene.show();
  }

  @FXML
  private void configureTracker(ActionEvent event) {
    Stage stage = newModalWindow();
    ConfigureTrackerScene scene = new ConfigureTrackerScene(stage, parent);
    scene.show();
  }
}
