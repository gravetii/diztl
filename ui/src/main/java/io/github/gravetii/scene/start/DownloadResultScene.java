package io.github.gravetii.scene.start;

import io.github.gravetii.client.handler.DownloadResult;
import io.github.gravetii.scene.FxSubScene;
import io.github.gravetii.scene.download.DownloadResultComponent;
import javafx.stage.Stage;

public class DownloadResultScene extends FxSubScene {
  private DownloadResultComponent downloadResultComponent;

  protected DownloadResultScene(Stage stage) throws Exception {
    super(stage);
    downloadResultComponent = new DownloadResultComponent(stage);
  }

  @Override
  protected void build() {
    root.setCenter(downloadResultComponent.getNode());
  }

  public void show(DownloadResult result) {
    downloadResultComponent.getController().show(result);
  }
}
