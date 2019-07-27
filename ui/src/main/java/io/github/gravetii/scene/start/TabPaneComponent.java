package io.github.gravetii.scene.start;

import io.github.gravetii.controller.start.TabPaneController;
import io.github.gravetii.scene.FxComponent;
import javafx.scene.Node;
import javafx.scene.control.TabPane;

public class TabPaneComponent extends FxComponent<TabPaneController, TabPane> {

  protected TabPaneComponent() {
    super("tabs.fxml");
    create();
  }

  @Override
  protected TabPaneController createController() {
    return new TabPaneController();
  }

  @Override
  protected TabPane createNode() {
    return loadNode();
  }

  public void addTab(String title, Node node) {
    addTab(title, node, true, true);
  }

  public void addTab(String title, Node node, boolean closable, boolean active) {
    getController().addTab(title, node, closable, active);
  }
}
