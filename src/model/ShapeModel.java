package model;

import java.util.LinkedList;
import java.util.List;
import java.util.Observable;
import java.util.function.Consumer;

public class ShapeModel extends Observable {

    private final List<DrawableShape> shapes;

    public ShapeModel() {
        shapes = new LinkedList<>();
    }

    public List<DrawableShape> getShapes() {
        return shapes;
    }

    public void updateShape(DrawableShape shape, Consumer<DrawableShape> updater) {
        if(shapes.contains(shape)) {
            updater.accept(shape);
            updateObservers();
        }
    }

    public void addShape(DrawableShape shape) {
        if(shape != null) {
            shapes.add(shape);
            updateObservers();
        }
    }

    public void removeShape(DrawableShape shape) {
        if(shapes.contains(shape)) {
            shapes.remove(shape);
            updateObservers();
        }
    }

    private void updateObservers() {
        super.setChanged();
        super.notifyObservers();
    }

}
