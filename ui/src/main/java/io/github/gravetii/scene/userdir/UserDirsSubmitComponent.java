package io.github.gravetii.scene.userdir;

import io.github.gravetii.controller.userdirs.UserDirsSubmitController;
import io.github.gravetii.scene.FxComponent;
import javafx.scene.layout.AnchorPane;

public class UserDirsSubmitComponent extends FxComponent<UserDirsSubmitController, AnchorPane> {
  protected UserDirsSubmitComponent() throws Exception {
    super("okAndCancel.fxml");
    this.create();
  }

  @Override
  protected UserDirsSubmitController createController() {
    return new UserDirsSubmitController();
  }

  @Override
  protected AnchorPane createNode() throws Exception {
    return this.loadNode();
  }
}
