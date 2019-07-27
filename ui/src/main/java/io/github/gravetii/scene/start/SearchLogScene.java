package io.github.gravetii.scene.start;

import io.github.gravetii.controller.start.FileResult;
import io.github.gravetii.scene.FxScene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Region;
import javafx.stage.Stage;

public class SearchLogScene extends FxScene {
  private FileSearchComponent fileSearchComponent;
  private LogComponent logComponent;
  private ResultListComponent resultListComponent;
  private TabPaneComponent tabPaneComponent;

  protected SearchLogScene(Stage stage, StartScene parent) throws Exception {
    super(stage, new BorderPane());
    fileSearchComponent = new FileSearchComponent(parent);
    logComponent = new LogComponent(stage);
    resultListComponent = new ResultListComponent(stage, parent);
    tabPaneComponent = new TabPaneComponent(stage);
  }

  @Override
  public Region build() {
    BorderPane pane = (BorderPane) root;
    pane.setLeft(fileSearchComponent.getNode());
    tabPaneComponent.addTab("log", logComponent.getNode());
    tabPaneComponent.addTab("Search", resultListComponent.getNode());
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

  public void show(FileResult result) {
    resultListComponent.getController().show(result);
  }
}
