package io.github.gravetii.scene.start;

import io.github.gravetii.scene.FxScene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Region;
import javafx.stage.Stage;

public class FileSearchScene extends FxScene {
  private FileSearchComponent fileSearchComponent;
  private QuickOptionsComponent quickOptionsComponent;

  protected FileSearchScene(Stage stage, StartScene scene) {
    super(stage, new BorderPane());
    fileSearchComponent = new FileSearchComponent(scene);
    quickOptionsComponent = new QuickOptionsComponent(stage, scene);
  }

  @Override
  public Region build() {
    BorderPane pane = (BorderPane) root;
    pane.setCenter(fileSearchComponent.getNode());
    pane.setBottom(quickOptionsComponent.getNode());
    return pane;
  }

  @Override
  protected String title() {
    return "Search scene";
  }
}
