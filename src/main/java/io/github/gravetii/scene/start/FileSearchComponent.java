package io.github.gravetii.scene.start;

import io.github.gravetii.client.DiztlClient;
import io.github.gravetii.controller.start.FileSearchController;
import io.github.gravetii.scene.FxComponent;
import javafx.scene.layout.AnchorPane;

public class FileSearchComponent extends FxComponent<FileSearchController, AnchorPane> {
  private final DiztlClient client;
  private final StartScene parent;

  public FileSearchComponent(DiztlClient client, StartScene parent) {
    super("filesearch.fxml");
    this.client = client;
    this.parent = parent;
    this.create();
  }

  @Override
  protected FileSearchController createController() {
    return new FileSearchController(client, parent);
  }

  @Override
  protected AnchorPane createNode() {
    return this.loadNode();
  }
}
