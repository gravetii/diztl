package io.github.gravetii.scene.start;

import io.github.gravetii.client.handler.DownloadResult;
import io.github.gravetii.controller.start.FileResult;
import io.github.gravetii.gen.Diztl;
import io.github.gravetii.scene.FxDimensions;
import io.github.gravetii.scene.FxScene;
import javafx.geometry.Dimension2D;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.util.Optional;

public class StartScene extends FxScene {
  private StartSubScene subscene;

  public StartScene(Stage stage) throws Exception {
    super(stage, new BorderPane());
    this.subscene = new StartSubScene(stage, this);
  }

  @Override
  protected void build() {
    subscene.build();
    root.setCenter(subscene.getRoot());
  }

  @Override
  protected String title() {
    return "Diztl";
  }

  @Override
  protected Optional<FxDimensions> preferredDimensions() {
    FxDimensions dimensions = new FxDimensions(new Dimension2D(600, 750));
    return Optional.of(dimensions);
  }

  public void show(Diztl.FileMetadata file, Diztl.Node source) {
    FileResult result = new FileResult(file, source);
    subscene.show(result);
  }

  public void reset() {
    subscene.reset();
  }

  public void show(DownloadResult result) {
    subscene.show(result);
  }
}
