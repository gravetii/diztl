package io.github.gravetii.scene.start;

import io.github.gravetii.controller.start.FileSearchController;
import io.github.gravetii.scene.FxComponent;
import javafx.scene.layout.AnchorPane;

public class FileSearchComponent extends FxComponent<FileSearchController, AnchorPane> {
  private StartScene parent;

  public FileSearchComponent(StartScene parent) throws Exception {
    super("fileSearch.fxml");
    this.parent = parent;
    this.create();
  }

  @Override
  protected FileSearchController createController() {
    return new FileSearchController(parent);
  }

  @Override
  protected AnchorPane createNode() throws Exception {
    return this.loadNode();
  }
}
