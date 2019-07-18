package io.github.gravetii.scene.start;

import io.github.gravetii.client.handler.DownloadResult;
import io.github.gravetii.controller.start.FileResult;
import io.github.gravetii.gen.Diztl;
import io.github.gravetii.scene.FxDimensions;
import io.github.gravetii.scene.FxScene;
import io.github.gravetii.scene.download.DownloadResultComponent;
import javafx.geometry.Dimension2D;
import javafx.stage.Stage;

import java.util.Optional;

public class StartScene extends FxScene {
  private FileSearchComponent fileSearchComponent;
  private ResultListComponent resultListComponent;
  private DownloadResultComponent downloadResultComponent;

  public StartScene(Stage stage) throws Exception {
    super(stage);
    this.fileSearchComponent = new FileSearchComponent(this);
    this.resultListComponent = new ResultListComponent(stage, this);
    this.downloadResultComponent = new DownloadResultComponent(stage);
  }

  @Override
  protected void build() {
    this.showLeft(fileSearchComponent)
        .showCenter(resultListComponent)
        .showBottom(downloadResultComponent);
  }

  @Override
  protected String title() {
    return "Diztl";
  }

  @Override
  protected Optional<FxDimensions> preferredDimensions() {
    FxDimensions dimensions = new FxDimensions(new Dimension2D(Double.MAX_VALUE, Double.MAX_VALUE));
    return Optional.of(dimensions);
  }

  public void showFileResult(Diztl.FileMetadata file, Diztl.Node source) {
    FileResult result = new FileResult(file, source);
    this.resultListComponent.getController().show(result);
  }

  public void reset() {
    this.resultListComponent.getController().reset();
  }

  public void showDownloadResult(DownloadResult result) {
    this.downloadResultComponent.getController().show(result);
  }
}
