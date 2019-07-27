package io.github.gravetii.scene.start;

import io.github.gravetii.scene.FxScene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Region;
import javafx.stage.Stage;

public class SearchLogScene extends FxScene {
  private StartScene parent;
  private FileSearchComponent fileSearchComponent;
  private LogComponent logComponent;
  private LogComponent errorLogComponent;
  private TabPaneComponent tabPaneComponent;

  protected SearchLogScene(Stage stage, StartScene parent) {
    super(stage, new BorderPane());
    this.parent = parent;
    fileSearchComponent = new FileSearchComponent(parent);
    logComponent = new LogComponent(false);
    errorLogComponent = new LogComponent(true);
    tabPaneComponent = new TabPaneComponent();
  }

  @Override
  public Region build() {
    BorderPane pane = (BorderPane) root;
    pane.setLeft(fileSearchComponent.getNode());
    tabPaneComponent.addTab("log", logComponent.getNode(), false, true);
    tabPaneComponent.addTab("error log", errorLogComponent.getNode(), false, false);
    pane.setCenter(tabPaneComponent.getNode());
    return pane;
  }

  @Override
  protected String title() {
    return "Diztl";
  }

  public void writeToLog(String text) {
    logComponent.getController().write(text);
  }

  public void writeToErrorLog(String text) {
    errorLogComponent.getController().write(text);
  }

  public ResultListComponent addNewSearchTab(String searchTerm) {
    ResultListComponent component = new ResultListComponent(stage, parent);
    tabPaneComponent.addTab("Search - " + searchTerm, component.getNode());
    return component;
  }
}
