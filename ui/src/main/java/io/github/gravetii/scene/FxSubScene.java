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

  protected Stage getStage() {
    return stage;
  }

  protected BorderPane getRoot() {
    return root;
  }

  public BorderPane show() {
    build();
    return root;
  }

  protected abstract void build();
}
