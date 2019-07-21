package io.github.gravetii.controller.userdir;

import com.jfoenix.controls.JFXButton;
import io.github.gravetii.AppContext;
import io.github.gravetii.client.connection.CommunicationClient;
import io.github.gravetii.controller.FxController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.stage.Stage;

import java.util.List;

public class UserDirsSubmitController implements FxController {
  private Stage stage;
  private UserDirsController ref;

  @FXML private JFXButton okBtn;
  @FXML private JFXButton cancelBtn;

  public UserDirsSubmitController(Stage stage, UserDirsController ref) {
    this.stage = stage;
    this.ref = ref;
  }

  @FXML
  public void ok(ActionEvent event) {
    List<String> shareDirs = ref.getShareDirs();
    String outputDir = ref.getOutputDir();
    AppContext.updateShareDirs(shareDirs);
    AppContext.updateOutputDir(outputDir);
    CommunicationClient.get().updateUserDirs(shareDirs, outputDir);
    stage.close();
  }

  @FXML
  public void cancel(ActionEvent event) {
    stage.close();
  }
}
