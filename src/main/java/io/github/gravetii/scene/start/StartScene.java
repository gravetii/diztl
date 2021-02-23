package io.github.gravetii.scene.start;

import io.github.gravetii.grpc.FileMetadata;
import io.github.gravetii.indexer.FileIndexer;
import io.github.gravetii.indexer.IndexedFile;
import io.github.gravetii.model.DownloadResult;
import io.github.gravetii.scene.FxDimensions;
import io.github.gravetii.scene.FxScene;
import io.github.gravetii.scene.download.DownloadResultScene;
import io.github.gravetii.util.DiztlExecutorService;
import javafx.concurrent.Task;
import javafx.geometry.Dimension2D;
import javafx.geometry.Orientation;
import javafx.scene.control.SplitPane;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class StartScene extends FxScene {
  private final SearchLogScene searchLogScene;
  private final DownloadResultScene downloadResultScene;

  private final FileIndexer indexer;

  public StartScene() {
    super(new SplitPane());
    searchLogScene = new SearchLogScene(this);
    downloadResultScene = new DownloadResultScene();
    List<String> shareDirs = Collections.singletonList("/Users/s0d01bw/Documents");
    indexer = new FileIndexer(shareDirs);
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

  public void index() {
    Task<Integer> task =
        new Task<>() {
          @Override
          protected Integer call() throws Exception {
            List<IndexedFile> files = indexer.index();
            writeToLog("Finished indexing all " + files.size() + " shared files.");
            files.forEach(x -> writeToLog("Finished indexing file - " + x.getPath()));
            return files.size();
          }
        };

    DiztlExecutorService.execute(task);
  }
}
