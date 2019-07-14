package io.github.gravetii.scene.start;

import io.github.gravetii.controller.search.FileSearchController;
import io.github.gravetii.scene.FxComponent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

public class FileSearchComponent extends FxComponent<FileSearchController, AnchorPane> {
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
  protected AnchorPane createNode() throws Exception {
    return this.loadNode();
  }
}
