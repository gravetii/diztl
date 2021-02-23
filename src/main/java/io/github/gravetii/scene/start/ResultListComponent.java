package io.github.gravetii.scene.start;

import io.github.gravetii.client.DiztlClient;
import io.github.gravetii.controller.start.FileResult;
import io.github.gravetii.controller.start.ResultListController;
import io.github.gravetii.scene.FxComponent;
import javafx.scene.layout.AnchorPane;

public class ResultListComponent extends FxComponent<ResultListController, AnchorPane> {
  private final DiztlClient client;
  private final StartScene scene;

  public ResultListComponent(DiztlClient client, StartScene scene) {
    super("resultlist.fxml");
    this.client = client;
    this.scene = scene;
    this.create();
  }

  @Override
  protected ResultListController createController() {
    return new ResultListController(client, scene);
  }

  @Override
  protected AnchorPane createNode() {
    return this.loadNode();
  }

  public void show(FileResult result) {
    this.getController().show(result);
  }
}
