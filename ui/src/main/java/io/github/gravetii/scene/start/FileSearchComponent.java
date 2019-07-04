package io.github.gravetii.scene.start;

import io.github.gravetii.controller.FileSearchController;
import io.github.gravetii.scene.FxComponent;
import javafx.scene.layout.VBox;

public class FileSearchComponent extends FxComponent<FileSearchController, VBox> {
  private StartScene parent;

  public FileSearchComponent(StartScene parent) throws Exception {
    super("filesearch.fxml");
    this.parent = parent;
    this.create();
  }

  @Override
  protected FileSearchController createController() {
    return new FileSearchController(parent);
  }

  @Override
  protected VBox createNode() throws Exception {
    return this.loadNode();
  }
}
