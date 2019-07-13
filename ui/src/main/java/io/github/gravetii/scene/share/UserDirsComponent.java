package io.github.gravetii.scene.share;

import io.github.gravetii.controller.UserDirsController;
import io.github.gravetii.scene.FxComponent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.util.List;

public class UserDirsComponent extends FxComponent<UserDirsController, BorderPane> {
  private Stage stage;
  private UserDirsScene parent;

  protected UserDirsComponent(Stage stage, UserDirsScene parent) throws Exception {
    super("userDirs.fxml");
    this.stage = stage;
    this.parent = parent;
    this.create();
  }

  @Override
  protected UserDirsController createController() {
    return new UserDirsController(stage, parent);
  }

  @Override
  protected BorderPane createNode() throws Exception {
    return this.loadNode();
  }

  public void displayShareDirs(List<String> shareDirs) {
    this.getController().displayShareDirs(shareDirs);
  }
}
