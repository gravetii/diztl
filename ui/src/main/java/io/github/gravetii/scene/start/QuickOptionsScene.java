package io.github.gravetii.scene.start;

import io.github.gravetii.scene.FxScene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Region;
import javafx.stage.Stage;

public class QuickOptionsScene extends FxScene {
  private QuickOptionsComponent quickOptionsComponent;

  public QuickOptionsScene(Stage stage) {
    super(stage, new BorderPane());
    quickOptionsComponent = new QuickOptionsComponent();
  }

  @Override
  public Region build() {
    BorderPane pane = (BorderPane) root;
    pane.setCenter(quickOptionsComponent.getNode());
    return pane;
  }

  @Override
  protected String title() {
    return "Quick Options";
  }
}
