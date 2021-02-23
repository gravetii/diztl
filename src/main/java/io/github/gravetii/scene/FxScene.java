package io.github.gravetii.scene;

import javafx.scene.layout.Region;

import java.util.Optional;

public abstract class FxScene {
  public final Region root;

  protected FxScene(Region root) {
    this.root = root;
  }

  public abstract String title();

  public Optional<FxDimensions> preferredDimensions() {
    return Optional.empty();
  }
}
