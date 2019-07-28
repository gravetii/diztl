package io.github.gravetii.scene.userdir;

import io.github.gravetii.controller.userdir.UserDirsController;
import io.github.gravetii.scene.FxComponent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class UserDirsComponent extends FxComponent<UserDirsController, AnchorPane> {
  private Stage stage;
  private UserDirsScene parent;

  protected UserDirsComponent(Stage stage, UserDirsScene parent) {
    super("userDirs.fxml");
    this.stage = stage;
    this.parent = parent;
    create();
  }

  @Override
  protected UserDirsController createController() {
    return new UserDirsController(stage, parent);
  }

  @Override
  protected AnchorPane createNode() {
    return loadNode();
  }
}
