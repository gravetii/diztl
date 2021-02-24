package io.github.gravetii.scene.configuration;

import io.github.gravetii.controller.configuration.ConfigureTrackerController;
import io.github.gravetii.scene.FxComponent;
import io.github.gravetii.store.DBService;
import javafx.scene.layout.AnchorPane;

public class ConfigureTrackerComponent extends FxComponent<ConfigureTrackerController, AnchorPane> {

  private DBService dbService;

  protected ConfigureTrackerComponent(DBService dbService) {
    super("configureTracker.fxml");
    this.dbService = dbService;
    create();
  }

  @Override
  protected ConfigureTrackerController createController() {
    return new ConfigureTrackerController(dbService);
  }

  @Override
  protected AnchorPane createNode() {
    return loadNode();
  }
}
