package io.github.gravetii.scene;

import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public abstract class FxSubScene {
  protected Stage stage;
  protected BorderPane root;

  protected FxSubScene(Stage stage) {
    this.stage = stage;
    this.root = new BorderPane();
  }

  public Stage getStage() {
    return stage;
  }

  public BorderPane getRoot() {
    return root;
  }

  protected abstract void build();
}
