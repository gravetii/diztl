package io.github.gravetii.scene.userdir;

import io.github.gravetii.controller.ShareDirsController;
import io.github.gravetii.scene.FxComponent;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.util.List;

public class ShareDirsComponent extends FxComponent<ShareDirsController, HBox> {
  private Stage stage;
  private UserDirsScene parent;

  protected ShareDirsComponent(Stage stage, UserDirsScene parent) throws Exception {
    super("shareDirs.fxml");
    this.stage = stage;
    this.parent = parent;
    this.create();
  }

  @Override
  protected ShareDirsController createController() {
    return new ShareDirsController(stage, parent);
  }

  @Override
  protected HBox createNode() throws Exception {
    return this.loadNode();
  }

  public void displayDirs(List<String> dirs) {
    this.getController().displayDirs(dirs);
  }
}
