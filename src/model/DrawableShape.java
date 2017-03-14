package model;

import drawable.Drawable;
import shape.Shape;

import java.awt.*;

public interface DrawableShape extends Shape, Drawable {
    void setColor(Color color);

    void setSelected(boolean isSelected);

    boolean isSelected();
}
