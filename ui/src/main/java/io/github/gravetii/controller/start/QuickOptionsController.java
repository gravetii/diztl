package io.github.gravetii.controller.start;

import io.github.gravetii.controller.FxController;
import io.github.gravetii.scene.configuration.ConfigureTrackerScene;
import io.github.gravetii.scene.userdir.UserDirsScene;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class QuickOptionsController implements FxController {
  private static final Logger logger = LoggerFactory.getLogger(QuickOptionsController.class.getCanonicalName());

  @FXML private Button configureDirsBtn;
  @FXML private Button configureTrackerBtn;

  @FXML private void configureDirs(ActionEvent event) {
    Stage stage = new Stage();
    UserDirsScene scene = new UserDirsScene(stage);
    scene.show();
  }

  @FXML private void configureTracker(ActionEvent event) {
    Stage stage = new Stage();
    ConfigureTrackerScene scene = new ConfigureTrackerScene(stage);
    scene.show();
  }

}
