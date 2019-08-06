package io.github.gravetii.scene.start;

import io.github.gravetii.controller.start.QuickOptionsController;
import io.github.gravetii.scene.FxComponent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class QuickOptionsComponent extends FxComponent<QuickOptionsController, AnchorPane> {
  private Stage stage;
  private StartScene scene;

  public QuickOptionsComponent(Stage stage, StartScene scene) {
    super("quickOptions.fxml");
    this.stage = stage;
    this.scene = scene;
    create();
  }

  @Override
  protected QuickOptionsController createController() {
    return new QuickOptionsController(stage, scene);
  }

  @Override
  protected AnchorPane createNode() {
    return loadNode();
  }
}
