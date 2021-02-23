package io.github.gravetii.scene.start;

import io.github.gravetii.grpc.FileMetadata;
import io.github.gravetii.scene.FxScene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Region;
import javafx.stage.Stage;

/**
 * This class is responsible for laying out the file-search and log components of the start scene.
 * This scene is typically present in the top portion of the split pane of the start scene.
 */
public class SearchLogScene extends FxScene {
  private final StartScene parent;
  private final FileSearchScene fileSearchScene;
  private final LogComponent logComponent;
  private final LogComponent errorLogComponent;
  private final TabPaneComponent tabPaneComponent;

  public SearchLogScene(Stage stage, StartScene scene) {
    super(stage, new BorderPane());
    this.parent = scene;
    fileSearchScene = new FileSearchScene(stage, scene);
    logComponent = new LogComponent(false);
    errorLogComponent = new LogComponent(true);
    tabPaneComponent = new TabPaneComponent();
  }

  @Override
  public Region build() {
    BorderPane pane = (BorderPane) root;
    pane.setLeft(fileSearchScene.build());
    tabPaneComponent.addTab("log", logComponent.getNode(), false, true);
    tabPaneComponent.addTab("error log", errorLogComponent.getNode(), false, false);
    pane.setCenter(tabPaneComponent.getNode());
    return pane;
  }

  @Override
  public String title() {
    return "Diztl";
  }

  public synchronized void writeToLog(String text) {
    logComponent.getController().write(text);
  }

  public synchronized void writeToErrorLog(String text) {
    errorLogComponent.getController().write(text);
  }

  public ResultListComponent addNewSearchTab(String query) {
    ResultListComponent component = new ResultListComponent(stage, parent);
    tabPaneComponent.addTab("search - " + query, component.getNode());
    return component;
  }

  public ResultListComponent addNewFileListTab(FileMetadata file) {
    ResultListComponent component = new ResultListComponent(stage, parent);
    tabPaneComponent.addTab("file list - " + file.getName(), component.getNode());
    return component;
  }
}
