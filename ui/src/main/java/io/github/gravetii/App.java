package io.github.gravetii;

import io.github.gravetii.client.DiztlClient;
import io.github.gravetii.scene.FxScene;
import io.github.gravetii.scene.share.UserDirsScene;
import javafx.application.Application;
import javafx.stage.Stage;

public class App extends Application {
  public static void main(String[] args) {
    launch(args);
  }

  @Override
  public void start(Stage stage) throws Exception {
    FxScene scene = new UserDirsScene(stage);
    scene.show();
  }

  @Override
  public void init() throws Exception {
    DiztlClient.init();
  }
}
