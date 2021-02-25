package io.github.gravetii.controller.start;

import com.github.lalyos.jfiglet.FigletFont;
import io.github.gravetii.controller.FxController;
import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.fxml.FXML;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class LogController implements FxController {
  private static final Logger logger =
      LoggerFactory.getLogger(LogController.class.getCanonicalName());

  private static final SimpleDateFormat FORMAT = new SimpleDateFormat("yyyy-MM-dd, HH:mm:ss");

  @FXML private TextArea logArea;

  public LogController() {}

  @FXML
  public void initialize() throws Exception {
    String message = FigletFont.convertOneLine("DIZTL");
    logArea.appendText(message);
    logArea.appendText("\n\nPlease wait while we set things up for you...\n\n");
    setContextMenu();
  }

  private void setContextMenu() {
    ContextMenu menu = new ContextMenu();
    MenuItem clearMenuItem = new MenuItem("Clear All");
    clearMenuItem.setOnAction(e -> logArea.clear());
    clearMenuItem.disableProperty().bind(Bindings.isEmpty(logArea.textProperty()));
    menu.getItems().add(clearMenuItem);
    logArea.setContextMenu(menu);
  }

  public void write(String text) {
    String timestamp = FORMAT.format(Calendar.getInstance().getTime());
    Platform.runLater(
        () -> logArea.appendText("[" + timestamp + "] " + text + System.lineSeparator()));
  }
}
