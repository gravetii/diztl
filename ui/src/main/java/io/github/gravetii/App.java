package io.github.gravetii;

import io.github.gravetii.client.connection.CommunicationClient;
import io.github.gravetii.scene.start.StartScene;
import javafx.application.Application;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class App extends Application {
  private static final Logger logger = LoggerFactory.getLogger(App.class.getCanonicalName());

  public static void main(String[] args) {
    launch(args);
  }

  @Override
  public void start(Stage stage) throws Exception {
    StartScene scene = new StartScene(stage);
    scene.show();
    CommunicationClient.get().register("127.0.0.1");
    CommunicationClient.get().index(scene);
  }

  @Override
  public void init() {
    try {
      CommunicationClient.init();
    } catch (Exception e) {
      logger.error("Couldn't connect to node", e);
      System.exit(0);
    }
  }
}
