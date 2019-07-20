package io.github.gravetii.scene.start;

import io.github.gravetii.client.handler.DownloadResult;
import io.github.gravetii.controller.start.FileResult;
import io.github.gravetii.scene.FxSubScene;
import io.github.gravetii.scene.download.DownloadResultComponent;
import javafx.stage.Stage;

public class StartSubScene extends FxSubScene {
  private FileSearchComponent fileSearchComponent;
  private ResultListComponent resultListComponent;
  private DownloadResultComponent downloadResultComponent;

  protected StartSubScene(Stage stage, StartScene parent) throws Exception {
    super(stage);
    this.fileSearchComponent = new FileSearchComponent(parent);
    this.resultListComponent = new ResultListComponent(stage, parent);
    this.downloadResultComponent = new DownloadResultComponent(stage);
  }

  @Override
  protected void build() {
    root.setLeft(fileSearchComponent.getNode());
    root.setCenter(resultListComponent.getNode());
    root.setBottom(downloadResultComponent.getNode());
  }

  public void show(FileResult result) {
    resultListComponent.getController().show(result);
  }

  public void reset() {
    resultListComponent.getController().reset();
  }

  public void show(DownloadResult result) {
    downloadResultComponent.getController().show(result);
  }
}
