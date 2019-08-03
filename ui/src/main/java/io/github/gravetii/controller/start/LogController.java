package io.github.gravetii.controller.start;

import io.github.gravetii.controller.FxController;
import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.fxml.FXML;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;

public class LogController implements FxController {
  private boolean errorLog;
  @FXML private TextArea logArea;

  public LogController(boolean errorLog) {
    this.errorLog = errorLog;
  }

  @FXML
  public void initialize() {
    if (errorLog) {
      logArea.setId("errorLog");
    } else {
      logArea.setText("Welcome to Diztl.\n\nPlease wait while we set things up for you...\n\n");
    }

    setContextMenu();
  }

  private void setContextMenu() {
    ContextMenu menu = new ContextMenu();
    MenuItem clearMenuItem = new MenuItem("Clear All");
    clearMenuItem.setOnAction(
        event -> {
          logArea.clear();
        });

    clearMenuItem.disableProperty().bind(Bindings.isEmpty(logArea.textProperty()));
    menu.getItems().add(clearMenuItem);
    logArea.setContextMenu(menu);
  }

  public void write(String text) {
    Platform.runLater(() -> logArea.appendText(text + "\n"));
  }
}
