package io.github.gravetii.scene.userdir;

import io.github.gravetii.controller.userdir.UserDirsController;
import io.github.gravetii.scene.FxComponent;
import io.github.gravetii.store.DBService;
import javafx.scene.layout.AnchorPane;

public class UserDirsComponent extends FxComponent<UserDirsController, AnchorPane> {

  private final DBService dbService;

  protected UserDirsComponent(DBService dbService) {
    super("userDirs.fxml");
    this.dbService = dbService;
    create();
  }

  @Override
  protected UserDirsController createController() {
    return new UserDirsController(dbService);
  }

  @Override
  protected AnchorPane createNode() {
    return loadNode();
  }
}
