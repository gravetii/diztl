package io.github.gravetii.scene.start;

import io.github.gravetii.controller.start.ResultListController;
import io.github.gravetii.scene.FxComponent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ResultListComponent extends FxComponent<ResultListController, VBox> {
  private Stage stage;
  private BorderPane root;
  private StartScene scene;

  public ResultListComponent(Stage stage, BorderPane root, StartScene scene) throws Exception {
    super("resultlist.fxml");
    this.stage = stage;
    this.root = root;
    this.scene = scene;
    this.create();
  }

  @Override
  protected ResultListController createController() {
    return new ResultListController(stage, scene);
  }

  @Override
  protected VBox createNode() throws Exception {
    return this.loadNode();
  }
}
