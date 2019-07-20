package io.github.gravetii.scene.start;

import io.github.gravetii.controller.start.FileResult;
import io.github.gravetii.scene.FxSubScene;
import javafx.stage.Stage;

public class SearchResultScene extends FxSubScene {
  private StartScene parent;
  private FileSearchComponent fileSearchComponent;
  private ResultListComponent resultListComponent;

  protected SearchResultScene(Stage stage, StartScene parent) throws Exception {
    super(stage);
    this.parent = parent;
    fileSearchComponent = new FileSearchComponent(parent);
    resultListComponent = new ResultListComponent(stage, parent);
  }

  @Override
  protected void build() {
    root.setLeft(fileSearchComponent.getNode());
    root.setCenter(resultListComponent.getNode());
  }

  public void show(FileResult result) {
    resultListComponent.getController().show(result);
  }

  public void reset() {
    resultListComponent.getController().reset();
  }
}
