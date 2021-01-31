package io.github.gravetii.scene.start;

import io.github.gravetii.controller.start.FileResult;
import io.github.gravetii.controller.start.ResultListController;
import io.github.gravetii.scene.FxComponent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class ResultListComponent extends FxComponent<ResultListController, AnchorPane> {
  private final Stage stage;
  private final StartScene scene;

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

  public void show(FileResult result) {
    this.getController().show(result);
  }
}
