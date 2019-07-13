package io.github.gravetii.scene.start;

import io.github.gravetii.controller.search.FileResult;
import io.github.gravetii.gen.Diztl;
import io.github.gravetii.scene.FxDimensions;
import io.github.gravetii.scene.FxScene;
import javafx.geometry.Dimension2D;
import javafx.stage.Stage;

import java.util.Optional;

public class StartScene extends FxScene {
  private FileSearchComponent fileSearchComponent;
  private ResultListComponent resultListComponent;

  public StartScene(Stage stage) throws Exception {
    super(stage);
    this.fileSearchComponent = new FileSearchComponent(this);
    this.resultListComponent = new ResultListComponent(stage, root);
  }

  @Override
  protected void build() {
    this.showTop(fileSearchComponent).showBottom(resultListComponent);
  }

  @Override
  protected String title() {
    return "DIZTL";
  }

  @Override
  protected Optional<FxDimensions> preferredDimensions() {
    FxDimensions dimensions =
        new FxDimensions(
            new Dimension2D(600, 500), new Dimension2D(600, 500), new Dimension2D(600, 500));
    return Optional.of(dimensions);
  }

  public void show(Diztl.FileMetadata file, Diztl.Node source) {
    FileResult result = new FileResult(file, source);
    this.resultListComponent.getController().show(result);
  }

  public void reset() {
    this.resultListComponent.getController().reset();
  }
}
