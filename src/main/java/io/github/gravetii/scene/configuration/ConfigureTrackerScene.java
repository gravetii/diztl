package io.github.gravetii.scene.configuration;

import io.github.gravetii.scene.FxDimensions;
import io.github.gravetii.scene.FxScene;
import io.github.gravetii.scene.start.StartScene;
import javafx.geometry.Dimension2D;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.util.Optional;

public class ConfigureTrackerScene extends FxScene {
  private Stage stage;
  private StartScene scene;

  public ConfigureTrackerScene(Stage stage, StartScene scene) {
    super(stage, new BorderPane());
    ConfigureTrackerComponent component = new ConfigureTrackerComponent(stage, scene);
    BorderPane pane = (BorderPane) root;
    pane.setCenter(component.getNode());
  }

  @Override
  public String title() {
    return "Configure tracker";
  }

  public Optional<FxDimensions> preferredDimensions() {
    FxDimensions dimensions =
        new FxDimensions(
            new Dimension2D(350, 150), new Dimension2D(350, 150), new Dimension2D(350, 150));
    return Optional.of(dimensions);
  }
}
