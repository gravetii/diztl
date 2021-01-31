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

public class LogController implements FxController {
  private static final Logger logger =
      LoggerFactory.getLogger(LogController.class.getCanonicalName());

  private final boolean errorLog;
  @FXML private TextArea logArea;

  public LogController(boolean errorLog) {
    this.errorLog = errorLog;
  }

  @FXML
  public void initialize() throws Exception {
    if (errorLog) logArea.setId("errorLog");
    else {
      String message = FigletFont.convertOneLine("DIZTL");
      logArea.appendText(message);
      logArea.appendText("\n\nPlease wait while we set things up for you...\n\n");
    }

    setContextMenu();
  }

  private void setContextMenu() {
    ContextMenu menu = new ContextMenu();
    MenuItem clearMenuItem = new MenuItem("Clear All");
    clearMenuItem.setOnAction(event -> logArea.clear());
    clearMenuItem.disableProperty().bind(Bindings.isEmpty(logArea.textProperty()));
    menu.getItems().add(clearMenuItem);
    logArea.setContextMenu(menu);
  }

  public void write(String text) {
    Platform.runLater(() -> logArea.appendText(text + "\n"));
  }
}
