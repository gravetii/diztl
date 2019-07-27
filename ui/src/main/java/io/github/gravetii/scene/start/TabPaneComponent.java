package io.github.gravetii.scene.start;

import io.github.gravetii.controller.start.TabPaneController;
import io.github.gravetii.scene.FxComponent;
import javafx.scene.Node;
import javafx.scene.control.TabPane;
import javafx.stage.Stage;

public class TabPaneComponent extends FxComponent<TabPaneController, TabPane> {
  private Stage stage;

  protected TabPaneComponent(Stage stage) throws Exception {
    super("tabs.fxml");
    this.stage = stage;
    create();
  }

  @Override
  protected TabPaneController createController() {
    return new TabPaneController(stage);
  }

  @Override
  protected TabPane createNode() throws Exception {
    return loadNode();
  }

  public void addTab(String title, Node node) {
   addTab(title, node, true);
  }

  public void addTab(String title, Node node, boolean closable) {
    getController().addTab(title, node, closable);
  }
}
