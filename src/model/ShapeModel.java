package model;

import shape.Circle;
import shape.ShapeComposite;
import shape.ShapeFactory;
import shape.Vertex;

import java.awt.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Observable;
import java.util.function.Consumer;

public class ShapeModel extends Observable {

    private final List<DrawableShape> shapes;

    public ShapeModel() {
        shapes = new LinkedList<>();
        addShape(new DrawableShape(ShapeFactory.getCircle(Vertex.at(400, 300), 100)));
        ShapeComposite composite = new ShapeComposite();
        composite.addShape(Circle.get(Vertex.at(200, 200), 50));
        composite.addShape(Circle.get(Vertex.at(100, 100), 50));
        composite.addShape(Circle.get(Vertex.at(500, 300), 50));
        addShape(new DrawableShape(ShapeFactory.getCircle(Vertex.at(200, 200), 100)));
        addShape(new DrawableShape(ShapeFactory.getCircle(Vertex.at(700, 100), 100)));
        DrawableShape drawableComposite = new DrawableShape(composite);
        drawableComposite.setColor(Color.BLUE);

        addShape(drawableComposite);
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
            shapes.add(0, shape);
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
