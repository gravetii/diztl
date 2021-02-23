package io.github.gravetii.scene.download;

import io.github.gravetii.model.DownloadResult;
import io.github.gravetii.scene.FxScene;
import javafx.scene.layout.BorderPane;

/**
 * This class is responsible for laying out the download results view of the start scene. This is
 * where the current and past downloads are shown in the bottom portion of the split pane.
 */
public class DownloadResultScene extends FxScene {
  private final DownloadResultComponent component;

  public DownloadResultScene() {
    super(new BorderPane());
    component = new DownloadResultComponent();
    BorderPane pane = (BorderPane) root;
    pane.setCenter(component.getNode());
  }

  @Override
  public String title() {
    return "Downloads";
  }

  public void show(DownloadResult result) {
    component.getController().show(result);
  }
}
