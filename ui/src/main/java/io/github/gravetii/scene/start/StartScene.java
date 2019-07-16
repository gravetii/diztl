package io.github.gravetii.scene.start;

import io.github.gravetii.controller.start.DownloadResult;
import io.github.gravetii.controller.start.FileResult;
import io.github.gravetii.gen.Diztl;
import io.github.gravetii.scene.FxDimensions;
import io.github.gravetii.scene.FxScene;
import javafx.geometry.Dimension2D;
import javafx.scene.control.ProgressBar;
import javafx.stage.Stage;

import java.util.Optional;

public class StartScene extends FxScene {
  private FileSearchComponent fileSearchComponent;
  private ResultListComponent resultListComponent;
  private DownloadResultComponent downloadResultComponent;
//  private DownloadProgressComponent downloadProgressComponent;

  public StartScene(Stage stage) throws Exception {
    super(stage);
    this.fileSearchComponent = new FileSearchComponent(this);
    this.resultListComponent = new ResultListComponent(stage, root, this);
    this.downloadResultComponent = new DownloadResultComponent(stage);
//    this.downloadProgressComponent = new DownloadProgressComponent();
  }

  @Override
  protected void build() {
    this.showTop(fileSearchComponent)
            .showCenter(resultListComponent)
            .showBottom(downloadResultComponent);
  }

  @Override
  protected String title() {
    return "DIZTL";
  }

  @Override
  protected Optional<FxDimensions> preferredDimensions() {
    FxDimensions dimensions =
        new FxDimensions(new Dimension2D(600, 750));
    return Optional.of(dimensions);
  }

  public void showFileResult(Diztl.FileMetadata file, Diztl.Node source) {
    FileResult result = new FileResult(file, source);
    this.resultListComponent.getController().show(result);
  }

  public void reset() {
    this.resultListComponent.getController().reset();
  }

//  public ProgressBar getProgressBar() {
//    return downloadProgressComponent.getProgressBar();
//  }

  public void showDownloadResult(Diztl.FileMetadata file) {
    DownloadResult result = new DownloadResult(file);
    this.downloadResultComponent.getController().show(result);
  }
}
