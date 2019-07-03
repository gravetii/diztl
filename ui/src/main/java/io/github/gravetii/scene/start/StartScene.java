package io.github.gravetii.scene.start;

import io.github.gravetii.scene.FxDimensions;
import io.github.gravetii.scene.FxScene;
import javafx.geometry.Dimension2D;
import javafx.stage.Stage;

import java.util.Optional;

public class StartScene extends FxScene {
  private FileSearchComponent fileSearchComponent;

  public StartScene(Stage stage) throws Exception {
    super(stage);
    this.fileSearchComponent = new FileSearchComponent(stage, root);
  }

  @Override
  protected void build() {
    this.showTop(fileSearchComponent);
  }

  @Override
  protected String title() {
    return "DIZTL";
  }

  @Override
  protected Optional<FxDimensions> preferredDimensions() {
    FxDimensions dimensions = new FxDimensions(new Dimension2D(600, 400));
    return Optional.of(dimensions);
  }
}
