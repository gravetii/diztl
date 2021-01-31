package io.github.gravetii.controller.start;

import io.github.gravetii.controller.FxController;
import io.github.gravetii.scene.configuration.ConfigureTrackerScene;
import io.github.gravetii.scene.start.StartScene;
import io.github.gravetii.scene.userdir.UserDirsScene;
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

  private final Stage stage;
  private final StartScene scene;

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

  private void index() {
    // todo index documents here...
    //    CommunicationClient.get().index(scene);
    logger.info("Indexing files now...");
  }

  @FXML
  private void indexFiles(ActionEvent event) {
    Button yes = new Button("Yes");
    Button no = new Button("No");
    // todo: Raise alert to check if user wants to index files...
  }
}
