package io.github.gravetii.scene.start;

import io.github.gravetii.scene.FxScene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Region;
import javafx.stage.Stage;

public class SearchLogScene extends FxScene {
  private StartScene parent;
  private FileSearchComponent fileSearchComponent;
  private LogComponent logComponent;
  private TabPaneComponent tabPaneComponent;

  protected SearchLogScene(Stage stage, StartScene parent) throws Exception {
    super(stage, new BorderPane());
    this.parent = parent;
    fileSearchComponent = new FileSearchComponent(parent);
    logComponent = new LogComponent(stage);
    tabPaneComponent = new TabPaneComponent();
  }

  @Override
  public Region build() {
    BorderPane pane = (BorderPane) root;
    pane.setLeft(fileSearchComponent.getNode());
    tabPaneComponent.addTab("log", logComponent.getNode(), false);
    pane.setCenter(tabPaneComponent.getNode());
    return pane;
  }

  @Override
  protected String title() {
    return "Diztl";
  }

  public void writeToLog(String text) {
    logComponent.getController().writeToLog(text);
  }

  public ResultListComponent addNewSearchTab(String searchTerm) {
    ResultListComponent component = new ResultListComponent(stage, parent);
    tabPaneComponent.addTab("Search - " + searchTerm, component.getNode());
    return component;
  }
}
