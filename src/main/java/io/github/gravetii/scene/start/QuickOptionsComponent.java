package io.github.gravetii.scene.start;

import io.github.gravetii.client.DiztlClient;
import io.github.gravetii.controller.start.QuickOptionsController;
import io.github.gravetii.scene.FxComponent;
import io.github.gravetii.store.DBService;
import javafx.scene.layout.AnchorPane;

public class QuickOptionsComponent extends FxComponent<QuickOptionsController, AnchorPane> {

  private final DiztlClient client;
  private final DBService dbService;
  private final StartScene scene;

  public QuickOptionsComponent(DiztlClient client, DBService dbService, StartScene scene) {
    super("quickOptions.fxml");
    this.client = client;
    this.dbService = dbService;
    this.scene = scene;
    this.create();
  }

  @Override
  protected QuickOptionsController createController() {
    return new QuickOptionsController(client, dbService, scene);
  }

  @Override
  protected AnchorPane createNode() {
    return this.loadNode();
  }
}
