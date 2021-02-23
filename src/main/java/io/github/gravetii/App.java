package io.github.gravetii;

import com.google.inject.Guice;
import com.google.inject.Injector;
import io.github.gravetii.client.DiztlClient;
import io.github.gravetii.indexer.FileIndexer;
import io.github.gravetii.node.DiztlServiceImpl;
import io.github.gravetii.scene.FxScene;
import io.github.gravetii.scene.start.StartScene;
import io.github.gravetii.util.DiztlExecutorService;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

/** JavaFX App */
public class App extends Application {

  private static final Logger logger = LoggerFactory.getLogger(App.class.getCanonicalName());

  private static Injector injector;

  private Server server;

  public static void display(Stage stage, FxScene scene) {
    stage.setScene(new Scene(scene.root));
    stage.setTitle(scene.title());
    scene.preferredDimensions().ifPresent(x -> x.setFor(stage));
    stage.show();
  }

  @Override
  public void start(Stage stage) throws Exception {
    stage.setOnCloseRequest(
        event -> {
          DiztlExecutorService.close();
          server.shutdown();
          try {
            server.awaitTermination(2, TimeUnit.MINUTES);
            server.shutdownNow();
          } catch (InterruptedException e) {
            logger.error("Thread interrupted while waiting server termination", e);
          }
        });

    StartScene scene = App.injector.getInstance(StartScene.class);
    App.display(stage, scene);

    new Thread(
            () -> {
              try {
                this.startServer();
                logger.info("Started diztl server...");
                if (server != null) server.awaitTermination();
              } catch (Exception e) {
                logger.error("Error while starting diztl server", e);
              }
            })
        .start();

    scene.index();
  }

  private void startServer() throws IOException {
    DiztlClient client = App.injector.getInstance(DiztlClient.class);
    FileIndexer indexer = App.injector.getInstance(FileIndexer.class);
    ServerBuilder<?> builder =
        ServerBuilder.forPort(50035).addService(new DiztlServiceImpl(client, indexer));
    this.server = builder.build().start();
  }

  public static void main(String[] args) {
    logger.info("Launching application...");
    App.injector = Guice.createInjector(new StartupModule());
    launch();
  }
}
