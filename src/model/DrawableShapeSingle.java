package model;

import drawable.Drawable;
import drawable.DrawableFactory;
import shape.Vertex;
import shape.Shape;
import shape.ShapeVisitor;

import java.awt.*;
import java.util.List;
import java.util.function.Function;

class DrawableShapeSingle implements DrawableShape {

    private Shape shape;
    private double strokeWidth;
    private Color color;
    private boolean isSelected;

    public DrawableShapeSingle(Shape shape) {
        this.shape = shape;
        strokeWidth = 5;
        color = Color.BLACK;
        isSelected = false;
    }

    @Override
    public void setColor(Color color) {
        this.color = color;
    }

    @Override
    public void setSelected(boolean isSelected) {
        this.isSelected = isSelected;
    }

    @Override
    public boolean isSelected() {
        return isSelected;
    }

    @Override
    public DrawableShape moveCenter(Vertex distance) {
        shape = shape.moveCenter(distance);
        return this;
    }

    @Override
    public void draw(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setStroke(new BasicStroke((float) strokeWidth));
        g.setColor(color);

        Function<Shape, Drawable> drawableFunction = isSelected? DrawableFactory::createMarked : DrawableFactory::create;
        drawableFunction.apply(shape).draw(g);
    }

    @Override
    public List<Vertex> getVertices() {
        return shape.getVertices();
    }

    @Override
    public Vertex getCenter() {
        return shape.getCenter();
    }

    @Override
    public boolean hasPoint(Vertex vertex) {
        return shape.hasPoint(vertex);
    }

    @Override
    public void accept(ShapeVisitor visitor) {
        shape.accept(visitor);
    }
}
