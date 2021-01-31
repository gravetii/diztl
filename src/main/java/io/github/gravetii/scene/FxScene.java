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

  public abstract Region build();

  protected abstract String title();

  protected Optional<FxDimensions> preferredDimensions() {
    return Optional.empty();
  }

  private void setDimensions() {
    preferredDimensions().ifPresent(dimensions -> dimensions.setFor(stage));
  }

  public void show() {
    build();
    Scene scene = new Scene(root);
    stage.setScene(scene);
    stage.setTitle(title());
    setDimensions();
    stage.show();
  }
}
