package io.github.gravetii.scene.start;

import io.github.gravetii.scene.FxDimensions;
import io.github.gravetii.scene.FxScene;
import javafx.geometry.Dimension2D;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Region;
import javafx.stage.Stage;

import java.util.Optional;

public class LogScene extends FxScene {
  private LogComponent logComponent;

  public LogScene(Stage stage) throws Exception {
    super(stage, new BorderPane());
    this.logComponent = new LogComponent(stage);
  }

  @Override
  public Region build() {
    BorderPane pane = (BorderPane) root;
    pane.setCenter(logComponent.getNode());
    return pane;
  }

  @Override
  protected String title() {
    return "Diztl";
  }

  @Override
  protected Optional<FxDimensions> preferredDimensions() {
    FxDimensions dimensions =
        new FxDimensions(
            new Dimension2D(Double.MAX_VALUE, Double.MAX_VALUE),
            new Dimension2D(1100, 820),
            new Dimension2D(Double.MAX_VALUE, Double.MAX_VALUE));
    return Optional.of(dimensions);
  }

  public void showLog(String text) {
    logComponent.getController().writeToLog(text);
  }
}
