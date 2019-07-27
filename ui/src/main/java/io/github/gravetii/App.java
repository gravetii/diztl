package io.github.gravetii;

import io.github.gravetii.client.connection.CommunicationClient;
import io.github.gravetii.client.handler.ExecutionHandler;
import io.github.gravetii.scene.start.StartScene;
import javafx.application.Application;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.Future;

public class App extends Application {
  private static final Logger logger = LoggerFactory.getLogger(App.class.getCanonicalName());

  public static void main(String[] args) {
    setUserAgentStylesheet(STYLESHEET_CASPIAN);
    launch(args);
  }

  @Override
  public void start(Stage stage) throws Exception {
    StartScene scene = new StartScene(stage);
    scene.show();
    this.bootstrap(scene);
  }

  private void bootstrap(StartScene scene) {
    Future f =
        ExecutionHandler.get()
            .submit(
                () -> {
                  CommunicationClient.get().index(scene);
                  CommunicationClient.get().register("127.0.0.1", scene);
                });
  }
}
