package io.github.gravetii.scene.userdir;

import io.github.gravetii.controller.userdir.UserDirsSubmitController;
import io.github.gravetii.scene.FxComponent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class UserDirsSubmitComponent extends FxComponent<UserDirsSubmitController, AnchorPane> {
  private Stage stage;
  private UserDirsComponent ref;

  protected UserDirsSubmitComponent(Stage stage, UserDirsComponent ref) {
    super("submitButtons.fxml");
    this.stage = stage;
    this.ref = ref;
    this.create();
  }

  @Override
  protected UserDirsSubmitController createController() {
    return new UserDirsSubmitController(stage, this.ref.getController());
  }

  @Override
  protected AnchorPane createNode() {
    return this.loadNode();
  }
}
