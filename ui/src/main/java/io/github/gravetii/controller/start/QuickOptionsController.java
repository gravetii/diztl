package io.github.gravetii.controller.start;

import com.jfoenix.animation.alert.JFXAlertAnimation;
import com.jfoenix.controls.JFXAlert;
import com.jfoenix.controls.JFXButton;
import io.github.gravetii.client.connection.CommunicationClient;
import io.github.gravetii.controller.FxController;
import io.github.gravetii.scene.configuration.ConfigureTrackerScene;
import io.github.gravetii.scene.start.StartScene;
import io.github.gravetii.scene.userdir.UserDirsScene;
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

  private Stage stage;
  private StartScene scene;

  @FXML private Button configureDirsBtn;
  @FXML private Button configureTrackerBtn;
  @FXML private Button indexFilesBtn;

  public QuickOptionsController(Stage stage, StartScene scene) {
    this.stage = stage;
    this.scene = scene;
  }

  @FXML
  private void initialize() {
    configureDirsBtn.setTooltip(new Tooltip("Configure your share and download folders"));
    configureTrackerBtn.setTooltip(new Tooltip("Configure tracker"));
    indexFilesBtn.setTooltip(new Tooltip("Index your share files"));
  }

  private Stage newModalWindow() {
    Stage stage = new Stage();
    stage.initOwner(this.stage);
    stage.setResizable(false);
    stage.initModality(Modality.APPLICATION_MODAL);
    return stage;
  }

  @FXML
  private void configureDirs(ActionEvent event) {
    Stage stage = newModalWindow();
    UserDirsScene scene = new UserDirsScene(stage, this.scene);
    scene.show();
  }

  @FXML
  private void configureTracker(ActionEvent event) {
    Stage stage = newModalWindow();
    ConfigureTrackerScene scene = new ConfigureTrackerScene(stage, this.scene);
    scene.show();
  }

  @FXML
  private void indexFiles(ActionEvent event) {
    JFXButton yes = new JFXButton("Yes");
    JFXButton no = new JFXButton("No");
    JFXAlert<Void> alert =
        Utils.booleanAlert(
            stage,
            "Re-index files?\n\nDo this only if you've added new files in your share directories.",
            true,
            yes,
            no);
    yes.setOnAction(
        e -> {
          logger.debug("Removed call to index here...");
          alert.close();
        });
    alert.setAnimation(JFXAlertAnimation.TOP_ANIMATION);
    alert.show();
  }
}
