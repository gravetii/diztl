package io.github.gravetii.scene.start;

import io.github.gravetii.client.DiztlClient;
import io.github.gravetii.scene.FxScene;
import io.github.gravetii.store.DBService;
import javafx.scene.layout.BorderPane;

public class FileSearchScene extends FxScene {

  protected FileSearchScene(DiztlClient client, DBService dbService, StartScene scene) {
    super(new BorderPane());
    FileSearchComponent fileSearchComponent = new FileSearchComponent(client, scene);
    QuickOptionsComponent quickOptionsComponent = new QuickOptionsComponent(client, dbService, scene);
    BorderPane pane = (BorderPane) root;
    pane.setCenter(fileSearchComponent.getNode());
    pane.setBottom(quickOptionsComponent.getNode());
  }

  @Override
  public String title() {
    return "Search scene";
  }
}
