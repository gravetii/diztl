package io.github.gravetii.scene.start;

import io.github.gravetii.scene.FxScene;
import javafx.scene.layout.BorderPane;

public class FileSearchScene extends FxScene {

  protected FileSearchScene(StartScene scene) {
    super(new BorderPane());
    FileSearchComponent component = new FileSearchComponent(scene);
    BorderPane pane = (BorderPane) root;
    pane.setCenter(component.getNode());
  }

  @Override
  public String title() {
    return "Search scene";
  }
}
