package io.github.gravetii.scene.start;

import io.github.gravetii.client.DiztlClient;
import io.github.gravetii.scene.FxScene;
import javafx.scene.layout.BorderPane;

public class FileSearchScene extends FxScene {

  protected FileSearchScene(DiztlClient client, StartScene scene) {
    super(new BorderPane());
    FileSearchComponent component = new FileSearchComponent(client, scene);
    BorderPane pane = (BorderPane) root;
    pane.setCenter(component.getNode());
  }

  @Override
  public String title() {
    return "Search scene";
  }
}
