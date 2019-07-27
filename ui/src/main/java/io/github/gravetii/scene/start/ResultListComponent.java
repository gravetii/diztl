package io.github.gravetii.scene.start;

import io.github.gravetii.controller.start.ResultListController;
import io.github.gravetii.scene.FxComponent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class ResultListComponent extends FxComponent<ResultListController, AnchorPane> {
  private Stage stage;
  private StartScene scene;

  public ResultListComponent(Stage stage, StartScene scene) {
    super("resultlist.fxml");
    this.stage = stage;
    this.scene = scene;
    this.create();
  }

  @Override
  protected ResultListController createController() {
    return new ResultListController(stage, scene);
  }

  @Override
  protected AnchorPane createNode() {
    return this.loadNode();
  }
}
