package io.github.gravetii.scene;

import javafx.geometry.Dimension2D;
import javafx.stage.Stage;

public class FxDimensions {
  private Dimension2D min;
  private Dimension2D max;
  private Dimension2D def;

  public FxDimensions(Dimension2D def) {
    this(def, null, null);
  }

  public FxDimensions(Dimension2D def, Dimension2D min, Dimension2D max) {
    this.def = def;
    this.min = min;
    this.max = max;
  }

  public void setFor(Stage stage) {
    stage.setWidth(this.def.getWidth());
    stage.setHeight(this.def.getHeight());
    if (this.min != null) {
      stage.setMinWidth(this.min.getWidth());
      stage.setMinHeight(this.min.getHeight());
    }
    if (this.max != null) {
      stage.setMaxWidth(this.max.getWidth());
      stage.setMaxHeight(this.max.getHeight());
    }
  }

  @Override
  public String toString() {
    return "FxDimensions{" + "min=" + min + ", max=" + max + ", def=" + def + '}';
  }
}