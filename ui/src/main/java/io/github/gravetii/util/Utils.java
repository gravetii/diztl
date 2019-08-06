package io.github.gravetii.util;

import com.jfoenix.animation.alert.JFXAlertAnimation;
import com.jfoenix.controls.JFXAlert;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialogLayout;
import io.github.gravetii.gen.Diztl;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;
import javafx.stage.DirectoryChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.File;
import java.nio.file.Paths;

public class Utils {
  private static final String GLOBAL_STYLESHEET =
      ClassLoader.getSystemResource("style/style.css").toExternalForm();

  public static String humanReadableByteCount(long bytes) {
    int unit = 1000;
    if (bytes < unit) return bytes + " B";
    int exp = (int) (Math.log(bytes) / Math.log(unit));
    char pre = "kMGTPE".charAt(exp - 1);
    return String.format("%.1f %sB", bytes / Math.pow(unit, exp), pre);
  }

  public static Long getByteCount(double value, String unit) {
    long multiplier = 1000000;
    if (unit.equals("kB")) {
      multiplier = multiplier / 1000;
    } else if (unit.equals("GB")) {
      multiplier = multiplier * 1000;
    }

    return (long) (value * multiplier);
  }

  public static String chooseDir(Stage stage) {
    DirectoryChooser chooser = new DirectoryChooser();
    chooser.setTitle("Choose folder...");
    chooser.setInitialDirectory(new File(System.getProperty("user.dir")));
    File dir = chooser.showDialog(stage);
    if (dir != null) {
      return dir.getPath();
    }

    return null;
  }

  public static String getFullFilePath(Diztl.FileMetadata file) {
    return Paths.get(file.getDir(), file.getName()).toString();
  }

  public static void addGlobalStylesheet(Region region) {
    region.getStylesheets().add(GLOBAL_STYLESHEET);
  }

  public static JFXAlert<Void> booleanAlert(Stage stage, String heading, boolean overlayClose, Button yes, Button no) {
    JFXDialogLayout layout = new JFXDialogLayout();
    Utils.addGlobalStylesheet(layout);
    layout.setHeading(new Label(heading));
    JFXAlert<Void> alert = new JFXAlert<>(stage);
    alert.setOverlayClose(overlayClose);
    yes.setId("okBtn");
    yes.setDefaultButton(true);
    no.setId("cancelBtn");
    no.setCancelButton(true);
    no.setOnAction(event -> alert.close());
    ButtonBar bar = new ButtonBar();
    bar.getButtons().add(yes);
    bar.getButtons().add(no);
    bar.setId("btnBar");
    layout.setActions(bar);
    alert.setContent(layout);
    alert.initModality(Modality.APPLICATION_MODAL);
    return alert;
  }
}
