package model;

import model.DrawableShape;

@FunctionalInterface
public interface RemovedShapeObserver {

    void updateShapeRemoved(DrawableShape shape);

}
