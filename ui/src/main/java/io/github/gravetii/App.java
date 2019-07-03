package io.github.gravetii;

import io.github.gravetii.scene.FxScene;
import io.github.gravetii.scene.start.StartScene;
import javafx.application.Application;
import javafx.stage.Stage;

public class App extends Application {
  @Override
  public void start(Stage stage) throws Exception {
//    FXMLLoader loader = new FXMLLoader(ClassLoader.getSystemResource("fxml/filesearch.fxml"));
//    Parent root = loader.load();
//    stage.setTitle("Hello World");
//    stage.setScene(new Scene(root, 600, 400));
//    stage.show();

    FxScene scene = new StartScene(stage);
    scene.show();
  }

  public static void main(String[] args) {
    launch(args);
  }
}
