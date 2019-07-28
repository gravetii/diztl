package io.github.gravetii.scene.download;

import io.github.gravetii.client.handler.DownloadResult;
import io.github.gravetii.scene.FxScene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Region;
import javafx.stage.Stage;

/**
 * This class is responsible for laying out the download results view
 * of the start scene. This is where the current and past downloads
 * are shown in the bottom portion of the split pane.
 */
public class DownloadResultScene extends FxScene {
  private DownloadResultComponent downloadResultComponent;

  public DownloadResultScene(Stage stage) {
    super(stage, new BorderPane());
    downloadResultComponent = new DownloadResultComponent(stage);
  }

  @Override
  public Region build() {
    BorderPane pane = (BorderPane) root;
    pane.setCenter(downloadResultComponent.getNode());
    return pane;
  }

  @Override
  protected String title() {
    return "Downloads";
  }

  public void show(DownloadResult result) {
    downloadResultComponent.getController().show(result);
  }
}
