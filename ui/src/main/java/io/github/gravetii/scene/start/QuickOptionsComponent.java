package io.github.gravetii.scene.start;

import io.github.gravetii.controller.start.QuickOptionsController;
import io.github.gravetii.scene.FxComponent;
import javafx.scene.layout.AnchorPane;

public class QuickOptionsComponent extends FxComponent<QuickOptionsController, AnchorPane> {
  public QuickOptionsComponent() {
    super("quickOptions.fxml");
    create();
  }

  @Override
  protected QuickOptionsController createController() {
    return new QuickOptionsController();
  }

  @Override
  protected AnchorPane createNode() {
    return loadNode();
  }
}
