package io.github.gravetii.scene.start;

import io.github.gravetii.scene.FxScene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class FileSearchScene extends FxScene {

  protected FileSearchScene(Stage stage, StartScene scene) {
    super(stage, new BorderPane());
    FileSearchComponent component = new FileSearchComponent(scene);
    BorderPane pane = (BorderPane) root;
    pane.setCenter(component.getNode());
  }

  @Override
  public String title() {
    return "Search scene";
  }
}
