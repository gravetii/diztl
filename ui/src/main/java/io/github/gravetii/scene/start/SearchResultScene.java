package io.github.gravetii.scene.start;

import io.github.gravetii.controller.start.FileResult;
import io.github.gravetii.scene.FxScene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Region;
import javafx.stage.Stage;

public class SearchResultScene extends FxScene {
  private StartScene parent;
  private FileSearchComponent fileSearchComponent;
  private ResultListComponent resultListComponent;

  protected SearchResultScene(Stage stage, StartScene parent) throws Exception {
    super(stage, new BorderPane());
    this.parent = parent;
    fileSearchComponent = new FileSearchComponent(parent);
    resultListComponent = new ResultListComponent(stage, parent);
  }

  @Override
  public Region build() {
    BorderPane pane = (BorderPane) root;
    pane.setLeft(fileSearchComponent.getNode());
    pane.setCenter(resultListComponent.getNode());
    return pane;
  }

  @Override
  protected String title() {
    return "Search";
  }

  public void show(FileResult result) {
    resultListComponent.getController().show(result);
  }

  public void reset() {
    resultListComponent.getController().reset();
  }
}
