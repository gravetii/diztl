package io.github.gravetii.scene.configuration;

import io.github.gravetii.client.DiztlClient;
import io.github.gravetii.scene.FxDimensions;
import io.github.gravetii.scene.FxScene;
import io.github.gravetii.store.DBService;
import javafx.geometry.Dimension2D;
import javafx.scene.layout.BorderPane;

import java.util.Optional;

public class ConfigureTrackerScene extends FxScene {

  public ConfigureTrackerScene(DiztlClient client, DBService dbService) {
    super(new BorderPane());
    ConfigureTrackerComponent component = new ConfigureTrackerComponent(client, dbService);
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
