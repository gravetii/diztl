package io.github.gravetii.controller.start;

import io.github.gravetii.controller.FxController;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.stage.Stage;

public class TabPaneController implements FxController {
  private Stage stage;

  @FXML private TabPane tabPane;

  public TabPaneController(Stage stage) {
    this.stage = stage;
  }

  public void addTab(String title, Node node, boolean closable) {
    Tab tab = new Tab(title, node);
    tab.setClosable(closable);
    tabPane.getTabs().add(tab);
  }
}
