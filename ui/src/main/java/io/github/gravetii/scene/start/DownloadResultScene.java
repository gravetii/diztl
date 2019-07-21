package io.github.gravetii.scene.start;

import io.github.gravetii.client.handler.DownloadResult;
import io.github.gravetii.scene.FxScene;
import io.github.gravetii.scene.download.DownloadResultComponent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Region;
import javafx.stage.Stage;

public class DownloadResultScene extends FxScene {
  private DownloadResultComponent downloadResultComponent;
  private FooterComponent footerComponent;

  protected DownloadResultScene(Stage stage) throws Exception {
    super(stage, new BorderPane());
    downloadResultComponent = new DownloadResultComponent(stage);
    footerComponent = new FooterComponent(stage);
  }

  @Override
  public Region build() {
    BorderPane pane = (BorderPane) root;
    pane.setCenter(downloadResultComponent.getNode());
    pane.setBottom(footerComponent.getNode());
    return pane;
  }

  @Override
  protected String title() {
    return "Downloads";
  }

  public void show(DownloadResult result) {
    downloadResultComponent.getController().show(result);
  }

  public void showFooter(String text) {
    footerComponent.getController().show(text);
  }
}
