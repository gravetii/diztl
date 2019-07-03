package io.github.gravetii.scene;

import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.util.Optional;

public abstract class FxScene {
  protected Stage stage;
  protected BorderPane root;

  protected FxScene(Stage stage) {
    this.stage = stage;
    this.root = new BorderPane();
  }

  public FxScene showTop(FxComponent component) {
    this.root.setTop(component.getNode());
    return this;
  }

  public FxScene showLeft(FxComponent component) {
    this.root.setLeft(component.getNode());
    return this;
  }

  public FxScene showCenter(FxComponent component) {
    this.root.setCenter(component.getNode());
    return this;
  }

  public FxScene showRight(FxComponent component) {
    this.root.setRight(component.getNode());
    return this;
  }

  public FxScene showBottom(FxComponent component) {
    this.root.setBottom(component.getNode());
    return this;
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
