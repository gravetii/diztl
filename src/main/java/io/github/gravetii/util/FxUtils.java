package io.github.gravetii.util;

import io.github.gravetii.App;
import io.github.gravetii.scene.FxScene;
import javafx.scene.Scene;
import javafx.scene.layout.Region;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;

import java.io.File;

public class FxUtils {

  private static final String GLOBAL_STYLESHEET =
      App.class.getResource("style.css").toExternalForm();

  public static String chooseDir(Stage stage) {
    DirectoryChooser chooser = new DirectoryChooser();
    chooser.setTitle("Choose folder...");
    chooser.setInitialDirectory(new File(System.getProperty("user.dir")));
    File dir = chooser.showDialog(stage);
    return dir != null ? dir.getPath() : null;
  }

  public static void addGlobalStylesheet(Region region) {
    region.getStylesheets().add(GLOBAL_STYLESHEET);
  }

  public static void display(Stage stage, FxScene scene) {
    stage.setScene(new Scene(scene.root));
    stage.setTitle(scene.title());
    scene.preferredDimensions().ifPresent(x -> x.setFor(stage));
    stage.show();
  }
}
