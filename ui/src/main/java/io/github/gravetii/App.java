package io.github.gravetii;

import io.github.gravetii.client.DiztlClient;
import io.github.gravetii.scene.FxScene;
import io.github.gravetii.scene.start.StartScene;
import javafx.application.Application;
import javafx.stage.Stage;

public class App extends Application {
  @Override
  public void start(Stage stage) throws Exception {
    FxScene scene = new StartScene(stage);
    scene.show();
  }

  @Override
  public void init() throws Exception {
    DiztlClient.init();
  }

  public static void main(String[] args) {
    launch(args);
  }
}