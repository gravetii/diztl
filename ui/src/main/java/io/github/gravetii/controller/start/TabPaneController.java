package io.github.gravetii.controller.start;

import io.github.gravetii.controller.FxController;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;

public class TabPaneController implements FxController {
  @FXML private TabPane tabPane;

  public void addTab(String title, Node node, boolean closable, boolean active) {
    Tab tab = new Tab(title, node);
    tab.setClosable(closable);
    tabPane.getTabs().add(tab);
    if (active) {
      tabPane.getSelectionModel().select(tab);
    }
  }
}
