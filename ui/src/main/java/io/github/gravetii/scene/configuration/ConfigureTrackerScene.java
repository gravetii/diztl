package io.github.gravetii.scene.configuration;

import io.github.gravetii.scene.FxDimensions;
import io.github.gravetii.scene.FxScene;
import io.github.gravetii.scene.start.StartScene;
import javafx.geometry.Dimension2D;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Region;
import javafx.stage.Stage;

import java.util.Optional;

public class ConfigureTrackerScene extends FxScene {
  private Stage stage;
  private StartScene scene;
  private ConfigureTrackerComponent component;

  public ConfigureTrackerScene(Stage stage, StartScene scene) {
    super(stage, new BorderPane());
    component = new ConfigureTrackerComponent(stage, scene);
  }

  @Override
  public Region build() {
    BorderPane pane = (BorderPane) root;
    pane.setCenter(component.getNode());
    return pane;
  }

  @Override
  protected String title() {
    return "Configure tracker";
  }

  protected Optional<FxDimensions> preferredDimensions() {
    FxDimensions dimensions =
        new FxDimensions(
            new Dimension2D(350, 150), new Dimension2D(350, 150), new Dimension2D(350, 150));
    return Optional.of(dimensions);
  }
}
