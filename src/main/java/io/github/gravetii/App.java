package io.github.gravetii;

import com.google.inject.Guice;
import com.google.inject.Injector;
import io.github.gravetii.client.DiztlClient;
import io.github.gravetii.indexer.FileIndexer;
import io.github.gravetii.indexer.IndexedFile;
import io.github.gravetii.node.DiztlServiceImpl;
import io.github.gravetii.scene.start.StartScene;
import io.github.gravetii.store.DBService;
import io.github.gravetii.util.DiztlExecutorService;
import io.github.gravetii.util.FxUtils;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import javafx.application.Application;
import javafx.concurrent.Task;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

/** JavaFX App */
public class App extends Application {

  private static final Logger logger = LoggerFactory.getLogger(App.class.getCanonicalName());

  private static final int PORT = 50035;

  private Server server;

  @Override
  public void start(Stage stage) throws Exception {
    Injector injector = Guice.createInjector(new StartupModule());
    DBService dbService = injector.getInstance(DBService.class);
    StartScene scene = injector.getInstance(StartScene.class);
    FxUtils.display(stage, scene);
    DiztlClient client = injector.getInstance(DiztlClient.class);
    FileIndexer indexer = injector.getInstance(FileIndexer.class);
    this.register(scene, client, dbService);
    this.index(scene, indexer);

    new Thread(
            () -> {
              try {
                this.startServer(client, indexer);
                logger.info("Started diztl server...");
                if (server != null) server.awaitTermination();
              } catch (Exception e) {
                logger.error("Error while starting diztl server", e);
              }
            })
        .start();
  }

  private void register(StartScene scene, DiztlClient client, DBService dbService) {
    String tracker = dbService.getTrackerAddress();
    try {
      client.register();
      scene.writeToLog("Connected to tracker at " + tracker);
    } catch (Exception e) {
      scene.writeToLog("Unable to connect to tracker at " + tracker);
      logger.error("Error while connecting to tracker {}", tracker, e);
    }
  }

  private void index(StartScene scene, FileIndexer indexer) {
    Task<Integer> task =
        new Task<>() {
          @Override
          protected Integer call() {
            List<IndexedFile> files = indexer.index();
            scene.writeToLog("Finished indexing all " + files.size() + " shared files.");
            return files.size();
          }
        };

    DiztlExecutorService.execute(task);
  }

  private void startServer(DiztlClient client, FileIndexer indexer) throws IOException {
    ServerBuilder<?> builder =
        ServerBuilder.forPort(PORT).addService(new DiztlServiceImpl(client, indexer));
    this.server = builder.build().start();
  }

  @Override
  public void stop() {
    DiztlExecutorService.close();
    if (this.server != null) {
      server.shutdown();
      try {
        server.awaitTermination(2, TimeUnit.MINUTES);
        server.shutdownNow();
        logger.info("diztl server terminated...");
      } catch (InterruptedException e) {
        logger.error("Thread interrupted while waiting server termination", e);
      }
    }
  }

  public static void main(String[] args) {
    logger.info("Launching application...");
    launch();
  }
}
