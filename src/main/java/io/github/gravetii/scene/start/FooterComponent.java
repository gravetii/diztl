package io.github.gravetii.scene.start;

import io.github.gravetii.controller.start.FooterController;
import io.github.gravetii.scene.FxComponent;
import javafx.scene.control.ToolBar;
import javafx.stage.Stage;

public class FooterComponent extends FxComponent<FooterController, ToolBar> {
  private Stage stage;

  protected FooterComponent(Stage stage) {
    super("footer.fxml");
    this.stage = stage;
    this.create();
  }

  @Override
  protected FooterController createController() {
    return new FooterController(stage);
  }

  @Override
  protected ToolBar createNode() {
    return this.loadNode();
  }
}
