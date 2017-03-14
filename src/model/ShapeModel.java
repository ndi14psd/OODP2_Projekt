package model;

import shape.Circle;
import shape.ShapeComposite;
import shape.ShapeFactory;
import shape.Vertex;

import java.awt.*;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Consumer;

public class ShapeModel extends ShapeObservable {

    private final List<DrawableShape> shapes;

    public ShapeModel() {
        shapes = new LinkedList<>();
        addShape(new DrawableShapeSingle(ShapeFactory.getCircle(Vertex.at(400, 300), 100)));
        ShapeComposite composite = new ShapeComposite();
        composite.addShape(Circle.get(Vertex.at(200, 200), 50));
        composite.addShape(Circle.get(Vertex.at(100, 100), 50));
        composite.addShape(Circle.get(Vertex.at(500, 300), 50));
        addShape(new DrawableShapeSingle(ShapeFactory.getCircle(Vertex.at(200, 200), 100)));
        addShape(new DrawableShapeSingle(ShapeFactory.getCircle(Vertex.at(700, 100), 100)));
        DrawableShape drawableComposite = new DrawableShapeSingle(composite);
        drawableComposite.setColor(Color.BLUE);

        addShape(drawableComposite);
    }

    public List<DrawableShape> getShapes() {
        return shapes;
    }

    public void updateShape(DrawableShape shape, Consumer<DrawableShape> updater) {
        if(shapes.contains(shape)) {
            updater.accept(shape);
            super.notifyShapeUpdated();
        }
    }

    public void addShape(DrawableShape shape) {
        if(shape != null) {
            shapes.add(0, shape);
            super.notifyShapeAdded(shape);
        }
    }

    public void removeShape(DrawableShape shape) {
        if(shapes.contains(shape)) {
            shapes.remove(shape);
            super.notifyShapeRemoved(shape);
        }
    }

}
