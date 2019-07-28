package io.github.gravetii.scene.configuration;

import io.github.gravetii.controller.configuration.ConfigureTrackerController;
import io.github.gravetii.scene.FxComponent;
import javafx.scene.layout.AnchorPane;

public class ConfigureTrackerComponent extends FxComponent<ConfigureTrackerController, AnchorPane> {
  protected ConfigureTrackerComponent() {
    super("configureTracker.fxml");
    create();
  }

  @Override
  protected ConfigureTrackerController createController() {
    return new ConfigureTrackerController();
  }

  @Override
  protected AnchorPane createNode() {
    return loadNode();
  }
}
