package io.github.gravetii.scene.start;

import io.github.gravetii.controller.start.QuickOptionsController;
import io.github.gravetii.scene.FxComponent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class QuickOptionsComponent extends FxComponent<QuickOptionsController, AnchorPane> {
  private Stage stage;
  private StartScene parent;

  public QuickOptionsComponent(Stage stage, StartScene parent) {
    super("quickOptions.fxml");
    this.stage = stage;
    this.parent = parent;
    create();
  }

  @Override
  protected QuickOptionsController createController() {
    return new QuickOptionsController(stage, parent);
  }

  @Override
  protected AnchorPane createNode() {
    return loadNode();
  }
}
