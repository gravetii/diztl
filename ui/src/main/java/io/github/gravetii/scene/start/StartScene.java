package io.github.gravetii.scene.start;

import io.github.gravetii.client.handler.DownloadResult;
import io.github.gravetii.controller.start.FileResult;
import io.github.gravetii.gen.Diztl;
import io.github.gravetii.scene.FxDimensions;
import io.github.gravetii.scene.FxScene;
import javafx.geometry.Dimension2D;
import javafx.geometry.Orientation;
import javafx.scene.control.SplitPane;
import javafx.stage.Stage;

import java.util.Optional;

public class StartScene extends FxScene {
  private SearchResultScene searchResultScene;
  private DownloadResultScene downloadResultScene;

  public StartScene(Stage stage) throws Exception {
    super(stage, new SplitPane());
    searchResultScene = new SearchResultScene(stage, this);
    downloadResultScene = new DownloadResultScene(stage);
  }

  @Override
  protected void build() {
    SplitPane pane = (SplitPane) root;
    pane.setDividerPositions(0.67);
    pane.setOrientation(Orientation.VERTICAL);
    pane.getItems().addAll(searchResultScene.show(), downloadResultScene.show());
  }

  @Override
  protected String title() {
    return "Diztl";
  }

  @Override
  protected Optional<FxDimensions> preferredDimensions() {
    FxDimensions dimensions =
        new FxDimensions(
            new Dimension2D(800, 650),
            new Dimension2D(800, 650),
            new Dimension2D(Double.MAX_VALUE, Double.MAX_VALUE));
    return Optional.of(dimensions);
  }

  public void show(Diztl.FileMetadata file, Diztl.Node source) {
    FileResult result = new FileResult(file, source);
    searchResultScene.show(result);
  }

  public void reset() {
    searchResultScene.reset();
  }

  public void show(DownloadResult result) {
    downloadResultScene.show(result);
  }
}
