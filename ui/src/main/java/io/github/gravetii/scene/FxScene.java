package io.github.gravetii.scene;

import javafx.scene.Scene;
import javafx.scene.layout.Region;
import javafx.stage.Stage;

import java.util.Optional;

public abstract class FxScene {
  protected Stage stage;
  protected Region root;

  protected FxScene(Stage stage, Region root) {
    this.stage = stage;
    this.root = root;
  }

  protected abstract void build();

  protected abstract String title();

  protected Optional<FxDimensions> preferredDimensions() {
    return Optional.empty();
  }

  private void setDimensions() {
    this.preferredDimensions().ifPresent(dimensions -> dimensions.setFor(this.stage));
  }

  public void show() {
    this.build();
    Scene scene = new Scene(this.root);
    this.stage.setScene(scene);
    this.stage.setTitle(this.title());
    this.setDimensions();
    this.stage.show();
  }
}
