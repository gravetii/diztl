package io.github.gravetii.scene.configuration;

import io.github.gravetii.controller.configuration.ConfigureTrackerController;
import io.github.gravetii.scene.FxComponent;
import io.github.gravetii.scene.start.StartScene;

import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class ConfigureTrackerComponent extends FxComponent<ConfigureTrackerController, AnchorPane> {
  private final Stage stage;
  private final StartScene scene;

  protected ConfigureTrackerComponent(Stage stage, StartScene scene) {
    super("configureTracker.fxml");
    this.stage = stage;
    this.scene = scene;
    create();
  }

  @Override
  protected ConfigureTrackerController createController() {
    return new ConfigureTrackerController(stage, scene);
  }

  @Override
  protected AnchorPane createNode() {
    return loadNode();
  }
}
