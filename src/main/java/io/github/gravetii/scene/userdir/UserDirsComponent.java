package io.github.gravetii.scene.userdir;

import io.github.gravetii.controller.userdir.UserDirsController;
import io.github.gravetii.scene.FxComponent;
import javafx.scene.layout.AnchorPane;

public class UserDirsComponent extends FxComponent<UserDirsController, AnchorPane> {

  protected UserDirsComponent() {
    super("userDirs.fxml");
    create();
  }

  @Override
  protected UserDirsController createController() {
    return new UserDirsController();
  }

  @Override
  protected AnchorPane createNode() {
    return loadNode();
  }
}
