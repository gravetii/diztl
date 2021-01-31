package io.github.gravetii.scene.userdir;

import io.github.gravetii.controller.userdir.UserDirsController;
import io.github.gravetii.scene.FxComponent;
import io.github.gravetii.scene.start.StartScene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class UserDirsComponent extends FxComponent<UserDirsController, AnchorPane> {
  private final Stage stage;
  private final StartScene scene;

  protected UserDirsComponent(Stage stage, StartScene scene) {
    super("userDirs.fxml");
    this.stage = stage;
    this.scene = scene;
    create();
  }

  @Override
  protected UserDirsController createController() {
    return new UserDirsController(stage, scene);
  }

  @Override
  protected AnchorPane createNode() {
    return loadNode();
  }
}
