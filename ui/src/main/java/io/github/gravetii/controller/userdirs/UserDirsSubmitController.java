package io.github.gravetii.controller.userdirs;

import com.jfoenix.controls.JFXButton;
import io.github.gravetii.client.DiztlClient;
import io.github.gravetii.controller.FxController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.stage.Stage;

import java.util.List;

public class UserDirsSubmitController implements FxController {
  private Stage stage;
  private ShareDirsController ref;

  @FXML private JFXButton okBtn;
  @FXML private JFXButton cancelBtn;

  public UserDirsSubmitController(Stage stage, ShareDirsController ref) {
    this.stage = stage;
    this.ref = ref;
  }

  @FXML
  public void ok(ActionEvent event) {
    List<String> shareDirs = this.ref.getFinalDirs();
    DiztlClient.get().updateUserDirs(shareDirs, "");
    stage.close();
  }

  @FXML
  public void cancel(ActionEvent event) {
    stage.close();
  }
}
