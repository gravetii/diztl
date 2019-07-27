package io.github.gravetii.scene.start;

import io.github.gravetii.scene.FxScene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Region;
import javafx.stage.Stage;

public class SearchLogScene extends FxScene {
  private StartScene parent;
  private FileSearchComponent fileSearchComponent;
  private LogComponent logComponent;

  protected SearchLogScene(Stage stage, StartScene parent) throws Exception {
    super(stage, new BorderPane());
    this.parent = parent;
    fileSearchComponent = new FileSearchComponent(parent);
    logComponent = new LogComponent(stage);
  }

  @Override
  public Region build() {
    BorderPane pane = (BorderPane) root;
    pane.setLeft(fileSearchComponent.getNode());
    pane.setCenter(logComponent.getNode());
    return pane;
  }

  @Override
  protected String title() {
    return "Diztl";
  }

  public void writeToLog(String text) {
    logComponent.getController().writeToLog(text);
  }
}
