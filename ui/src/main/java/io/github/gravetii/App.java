package io.github.gravetii;

import com.jfoenix.animation.alert.JFXAlertAnimation;
import com.jfoenix.controls.JFXAlert;
import com.jfoenix.controls.JFXButton;
import io.github.gravetii.client.connection.CommunicationClient;
import io.github.gravetii.client.handler.ExecutionHandler;
import io.github.gravetii.scene.start.StartScene;
import io.github.gravetii.util.Utils;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.Future;

public class App extends Application {
  private static final Logger logger = LoggerFactory.getLogger(App.class.getCanonicalName());

  public static void main(String[] args) {
    logger.info("Starting application...");
    launch(args);
  }

  private static JFXAlert<Void> getCloseAlert(Stage stage) {
    JFXButton yes = new JFXButton("Yes");
    JFXButton no = new JFXButton("No");
    yes.setOnAction(event -> Platform.exit());
    JFXAlert<Void> alert = Utils.booleanAlert(stage, "Really Exit?", false, yes, no);
    alert.setAnimation(JFXAlertAnimation.CENTER_ANIMATION);
    return alert;
  }

  @Override
  public void start(Stage stage) throws Exception {
    stage.setOnCloseRequest(
        event -> {
          JFXAlert<Void> alert = getCloseAlert(stage);
          alert.show();
          event.consume();
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
                  CommunicationClient.get().register(scene);
                });
  }
}
