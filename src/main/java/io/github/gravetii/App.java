package io.github.gravetii;

import com.google.inject.Guice;
import com.google.inject.Injector;
import io.github.gravetii.client.DiztlClient;
import io.github.gravetii.indexer.FileIndexer;
import io.github.gravetii.indexer.IndexedFile;
import io.github.gravetii.node.DiztlServer;
import io.github.gravetii.node.DiztlServiceImpl;
import io.github.gravetii.scene.start.StartScene;
import io.github.gravetii.store.DBService;
import io.github.gravetii.util.DiztlExecutorService;
import io.github.gravetii.util.FxUtils;
import javafx.application.Application;
import javafx.concurrent.Task;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/** JavaFX App */
public class App extends Application {

  private static final Logger logger = LoggerFactory.getLogger(App.class.getCanonicalName());

  private static final Injector injector = Guice.createInjector(new StartupModule());

  private DiztlServer server;

  @Override
  public void start(Stage stage) throws Exception {
    StartScene scene = injector.getInstance(StartScene.class);
    FxUtils.display(stage, scene);
    DiztlServiceImpl service = injector.getInstance(DiztlServiceImpl.class);
    this.register();
    this.index();
    server = new DiztlServer(service);
    server.start();
  }

  private void register() {
    StartScene scene = injector.getInstance(StartScene.class);
    DiztlClient client = injector.getInstance(DiztlClient.class);
    DBService service = injector.getInstance(DBService.class);
    String tracker = service.getTrackerAddress();
    try {
      client.register();
      scene.writeToLog("Connected to tracker at " + "[" + tracker + "]");
    } catch (Exception e) {
      scene.writeToLog("Unable to connect to tracker at " + tracker);
      logger.error("Error while connecting to tracker {}", tracker, e);
    }
  }

  private void index() {
    StartScene scene = injector.getInstance(StartScene.class);
    FileIndexer indexer = injector.getInstance(FileIndexer.class);
    Task<Integer> task =
        new Task<>() {
          @Override
          protected Integer call() {
            scene.writeToLog("Indexing your files...hang tight!");
            List<IndexedFile> files = indexer.index();
            scene.writeToLog("Finished indexing all " + files.size() + " shared files.");
            return files.size();
          }
        };

    DiztlExecutorService.execute(task);
  }

  @Override
  public void stop() {
    DiztlExecutorService.close();
    if (server != null) server.stop();
  }

  public static void main(String[] args) {
    logger.info("Launching application...");
    launch();
  }
}
