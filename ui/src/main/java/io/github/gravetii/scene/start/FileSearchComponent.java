package io.github.gravetii.scene.start;

import io.github.gravetii.controller.FileSearchController;
import io.github.gravetii.scene.FxComponent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class FileSearchComponent extends FxComponent<FileSearchController, VBox> {
  private Stage stage;
  private BorderPane root;

  protected FileSearchComponent(Stage stage, BorderPane root) throws Exception {
    super("filesearch.fxml");
    this.stage = stage;
    this.root = root;
    this.create();
  }

  @Override
  protected FileSearchController createController() {
    return new FileSearchController(stage);
  }

  @Override
  protected VBox createNode() throws Exception {
    return this.loadNode();
  }
}
