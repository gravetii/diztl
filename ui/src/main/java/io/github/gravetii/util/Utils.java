package io.github.gravetii.util;

import io.github.gravetii.gen.Diztl;
import javafx.scene.layout.Region;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;

import java.io.File;
import java.nio.file.Paths;

public class Utils {
  private static final String GLOBAL_STYLESHEET = ClassLoader.getSystemResource("style/style.css").toExternalForm();

  public static String humanReadableByteCount(long bytes) {
    int unit = 1000;
    if (bytes < unit) return bytes + " B";
    int exp = (int) (Math.log(bytes) / Math.log(unit));
    char pre = "kMGTPE".charAt(exp - 1);
    return String.format("%.1f %sB", bytes / Math.pow(unit, exp), pre);
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
}
