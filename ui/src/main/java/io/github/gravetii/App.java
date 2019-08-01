package io.github.gravetii;

import com.jfoenix.animation.alert.JFXAlertAnimation;
import com.jfoenix.controls.JFXAlert;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialogLayout;
import io.github.gravetii.client.connection.CommunicationClient;
import io.github.gravetii.client.handler.ExecutionHandler;
import io.github.gravetii.scene.start.StartScene;
import io.github.gravetii.util.Utils;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.Label;
import javafx.stage.Modality;
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
    JFXDialogLayout layout = new JFXDialogLayout();
    Utils.addGlobalStylesheet(layout);
    layout.setHeading(new Label("Really Exit?"));
    JFXAlert<Void> alert = new JFXAlert<>(stage);
    alert.setOverlayClose(false);
    alert.setAnimation(JFXAlertAnimation.CENTER_ANIMATION);
    JFXButton yes = new JFXButton("Yes");
    yes.setId("okBtn");
    yes.setDefaultButton(true);
    JFXButton no = new JFXButton("No");
    no.setId("cancelBtn");
    no.setCancelButton(true);
    ButtonBar bar = new ButtonBar();
    bar.getButtons().add(yes);
    bar.getButtons().add(no);
    bar.setId("btnBar");
    layout.setActions(bar);
    yes.setOnAction(event -> Platform.exit());
    no.setOnAction(event -> alert.close());
    alert.setContent(layout);
    alert.initModality(Modality.APPLICATION_MODAL);
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
                  CommunicationClient.get().index(scene);
                  CommunicationClient.get().register(scene);
                });
  }
}
