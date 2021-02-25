package io.github.gravetii.scene.configuration;

import io.github.gravetii.client.DiztlClient;
import io.github.gravetii.controller.configuration.ConfigureTrackerController;
import io.github.gravetii.scene.FxComponent;
import io.github.gravetii.scene.start.StartScene;
import io.github.gravetii.store.DBService;
import javafx.scene.layout.AnchorPane;

public class ConfigureTrackerComponent extends FxComponent<ConfigureTrackerController, AnchorPane> {

  private final DiztlClient client;
  private final DBService dbService;
  private final StartScene scene;

  protected ConfigureTrackerComponent(DiztlClient client, DBService dbService, StartScene scene) {
    super("configureTracker.fxml");
    this.client = client;
    this.dbService = dbService;
    this.scene = scene;
    create();
  }

  @Override
  protected ConfigureTrackerController createController() {
    return new ConfigureTrackerController(client, dbService, scene);
  }

  @Override
  protected AnchorPane createNode() {
    return loadNode();
  }
}
