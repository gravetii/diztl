package io.github.gravetii.scene.configuration;

import io.github.gravetii.scene.FxDimensions;
import io.github.gravetii.scene.FxScene;
import javafx.geometry.Dimension2D;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Region;
import javafx.stage.Stage;

import java.util.Optional;

public class ConfigureTrackerScene extends FxScene {
  private Stage stage;
  private ConfigureTrackerComponent component;

  public ConfigureTrackerScene(Stage stage) {
    super(stage, new BorderPane());
    component = new ConfigureTrackerComponent();
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
    FxDimensions dimensions = new FxDimensions(new Dimension2D(350, 100),
            new Dimension2D(350, 100), new Dimension2D(350, 100));
    return Optional.of(dimensions);
  }
}
