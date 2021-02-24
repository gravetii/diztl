package io.github.gravetii.scene.start;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import io.github.gravetii.client.DiztlClient;
import io.github.gravetii.grpc.FileMetadata;
import io.github.gravetii.model.DownloadResult;
import io.github.gravetii.scene.FxDimensions;
import io.github.gravetii.scene.FxScene;
import io.github.gravetii.scene.download.DownloadResultScene;
import javafx.geometry.Dimension2D;
import javafx.geometry.Orientation;
import javafx.scene.control.SplitPane;
import javafx.stage.Stage;

import java.util.Optional;

@Singleton
public class StartScene extends FxScene {

  private final SearchLogScene searchLogScene;
  private final DownloadResultScene downloadResultScene;

  @Inject
  public StartScene(DiztlClient client) {
    super(new SplitPane());
    searchLogScene = new SearchLogScene(client, this);
    downloadResultScene = new DownloadResultScene();
    this.build();
  }

  public ResultListComponent addNewSearchTab(String query) {
    return searchLogScene.addNewSearchTab(query);
  }

  public ResultListComponent addNewFileListTab(FileMetadata file) {
    return searchLogScene.addNewFileListTab(file);
  }

  private void build() {
    SplitPane pane = (SplitPane) root;
    pane.setDividerPositions(0.70);
    pane.setOrientation(Orientation.VERTICAL);
    pane.getItems().addAll(searchLogScene.root, downloadResultScene.root);
  }

  @Override
  public String title() {
    return "Diztl";
  }

  @Override
  public Optional<FxDimensions> preferredDimensions() {
    FxDimensions dimensions =
        new FxDimensions(
            new Dimension2D(Double.MAX_VALUE, Double.MAX_VALUE),
            new Dimension2D(1100, 820),
            new Dimension2D(Double.MAX_VALUE, Double.MAX_VALUE));
    return Optional.of(dimensions);
  }

  public void show(DownloadResult result) {
    downloadResultScene.show(result);
  }

  public void writeToLog(String text) {
    searchLogScene.writeToLog(text);
  }

  public void writeToErrorLog(String text) {
    searchLogScene.writeToErrorLog(text);
  }

  public Stage getWindow() {
    return (Stage) root.getScene().getWindow();
  }
}
