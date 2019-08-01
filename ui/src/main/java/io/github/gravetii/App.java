package io.github.gravetii;

import io.github.gravetii.client.connection.CommunicationClient;
import io.github.gravetii.client.handler.ExecutionHandler;
import io.github.gravetii.scene.start.StartScene;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;
import java.util.concurrent.Future;

public class App extends Application {
  private static final Logger logger = LoggerFactory.getLogger(App.class.getCanonicalName());

  public static void main(String[] args) {
    logger.info("Starting application...");
    launch(args);
  }

  public static boolean close(Stage stage) {
    if (exitCheck(stage)) {
      Platform.exit();
      return true;
    }

    return false;
  }

  private static boolean exitCheck(Stage stage) {
    Alert alert =
        new Alert(Alert.AlertType.CONFIRMATION, "Are you sure?", ButtonType.NO, ButtonType.YES);
    alert.setHeaderText("");
    alert.setTitle("Really Exit?");
    alert.initOwner(stage);
    Optional<ButtonType> type = alert.showAndWait();
    return type.isPresent() && type.get() == ButtonType.YES;
  }

  @Override
  public void start(Stage stage) throws Exception {
    stage.setOnCloseRequest(
        event -> {
          if (!close(stage)) {
            event.consume();
          }
        });

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
                  CommunicationClient.get().register(scene);
                });
  }
}
