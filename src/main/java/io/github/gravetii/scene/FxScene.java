package io.github.gravetii.scene;

import javafx.scene.layout.Region;
import javafx.stage.Stage;

import java.util.Optional;

public abstract class FxScene {
  public final Stage stage;
  public final Region root;

  protected FxScene(Stage stage, Region root) {
    this.stage = stage;
    this.root = root;
  }

  public abstract String title();

  public Optional<FxDimensions> preferredDimensions() {
    return Optional.empty();
  }
}
