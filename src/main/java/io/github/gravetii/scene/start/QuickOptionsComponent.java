package io.github.gravetii.scene.start;

import io.github.gravetii.controller.start.QuickOptionsController;
import io.github.gravetii.scene.FxComponent;
import io.github.gravetii.store.DBService;
import javafx.scene.layout.AnchorPane;

public class QuickOptionsComponent extends FxComponent<QuickOptionsController, AnchorPane> {

  private final DBService dbService;
  private final StartScene parent;

  public QuickOptionsComponent(DBService dbService, StartScene parent) {
    super("quickOptions.fxml");
    this.dbService = dbService;
    this.parent = parent;
    this.create();
  }

  @Override
  protected QuickOptionsController createController() {
    return new QuickOptionsController(dbService, parent);
  }

  @Override
  protected AnchorPane createNode() {
    return this.loadNode();
  }
}
