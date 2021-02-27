package io.github.gravetii.scene.start;

import io.github.gravetii.client.DiztlClient;
import io.github.gravetii.grpc.FileMetadata;
import io.github.gravetii.scene.FxScene;
import io.github.gravetii.store.DBService;
import javafx.scene.layout.BorderPane;

/**
 * This class is responsible for laying out the file-search and log components of the start scene.
 * This scene is typically present in the top portion of the split pane of the start scene.
 */
public class SearchLogScene extends FxScene {
  private final DiztlClient client;
  private final DBService dbService;
  private final StartScene scene;
  private final LogComponent logComponent = new LogComponent();
  private final TabPaneComponent tabPaneComponent = new TabPaneComponent();

  public SearchLogScene(DiztlClient client, DBService dbService, StartScene scene) {
    super(new BorderPane());
    this.client = client;
    this.dbService = dbService;
    this.scene = scene;
    FileSearchScene fileSearchScene = new FileSearchScene(client, dbService, scene);
    BorderPane pane = (BorderPane) root;
    pane.setLeft(fileSearchScene.root);
    tabPaneComponent.addTab("log", logComponent.getNode(), false, true);
    pane.setCenter(tabPaneComponent.getNode());
  }

  @Override
  public String title() {
    return "Diztl";
  }

  public void writeToLog(String text) {
    logComponent.getController().write(text);
  }

  public ResultListComponent addNewSearchTab(String query) {
    ResultListComponent component = new ResultListComponent(client, dbService, scene);
    tabPaneComponent.addTab("search - " + query, component.getNode());
    return component;
  }

  public ResultListComponent addNewFileListTab(FileMetadata file) {
    ResultListComponent component = new ResultListComponent(client, dbService, scene);
    tabPaneComponent.addTab("file list - " + file.getName(), component.getNode());
    return component;
  }
}
