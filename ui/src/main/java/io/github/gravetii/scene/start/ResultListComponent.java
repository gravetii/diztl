package io.github.gravetii.scene.start;

import io.github.gravetii.controller.ResultListController;
import io.github.gravetii.scene.FxComponent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ResultListComponent extends FxComponent<ResultListController, VBox> {
  private Stage stage;
  private BorderPane root;

  public ResultListComponent(Stage stage, BorderPane root) throws Exception {
    super("resultlist.fxml");
    this.stage = stage;
    this.root = root;
    this.create();
  }

  @Override
  protected ResultListController createController() {
    return new ResultListController(stage);
  }

  @Override
  protected VBox createNode() throws Exception {
    return this.loadNode();
  }
}
