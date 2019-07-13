package io.github.gravetii.scene.share;

import io.github.gravetii.controller.ShareFoldersController;
import io.github.gravetii.scene.FxComponent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.util.List;

public class ShareFoldersComponent extends FxComponent<ShareFoldersController, BorderPane> {
  private Stage stage;
  private ShareFoldersScene parent;

  protected ShareFoldersComponent(Stage stage, ShareFoldersScene parent) throws Exception {
    super("shareFolders.fxml");
    this.stage = stage;
    this.parent = parent;
    this.create();
  }

  @Override
  protected ShareFoldersController createController() {
    return new ShareFoldersController(stage, parent);
  }

  @Override
  protected BorderPane createNode() throws Exception {
    return this.loadNode();
  }

  public void displayShareDirs(List<String> shareDirs) {
    this.getController().displayShareDirs(shareDirs);
  }
}
