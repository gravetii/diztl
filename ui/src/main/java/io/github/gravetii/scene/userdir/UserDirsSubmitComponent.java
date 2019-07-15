package io.github.gravetii.scene.userdir;

import io.github.gravetii.controller.userdir.UserDirsSubmitController;
import io.github.gravetii.scene.FxComponent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class UserDirsSubmitComponent extends FxComponent<UserDirsSubmitController, AnchorPane> {
  private Stage stage;
  private ShareDirsComponent ref;

  protected UserDirsSubmitComponent(Stage stage, ShareDirsComponent ref) throws Exception {
    super("okAndCancel.fxml");
    this.stage = stage;
    this.ref = ref;
    this.create();
  }

  @Override
  protected UserDirsSubmitController createController() {
    return new UserDirsSubmitController(stage, this.ref.getController());
  }

  @Override
  protected AnchorPane createNode() throws Exception {
    return this.loadNode();
  }
}
