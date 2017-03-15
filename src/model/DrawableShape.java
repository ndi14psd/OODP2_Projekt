package model;

import drawable.Drawable;
import drawable.ShapeDrawer;
import shape.Vertex;
import shape.Shape;
import shape.ShapeVisitor;

import java.awt.*;
import java.util.List;

public final class DrawableShape implements Shape, Drawable {

    private Shape shape;
    private double strokeWidth;
    private Color color;
    private boolean isSelected;

    public DrawableShape(Shape shape) {
        this.shape = shape;
        strokeWidth = 5;
        color = Color.BLACK;
        isSelected = false;
    }

    public void setColor(Color color) {
        this.color = color;
    }
    
    public Color getColor() {
    	return color;
    }

    public void setSelected(boolean isSelected) {
        this.isSelected = isSelected;
    }

    public boolean isSelected() {
        return isSelected;
    }

    @Override
    public DrawableShape setCenter(Vertex distance) {
        shape = shape.setCenter(distance);
        return this;
    }

    @Override
    public void draw(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setStroke(new BasicStroke((float) strokeWidth));
        g.setColor(isSelected? Color.CYAN : color);
        ShapeDrawer.draw(g, shape);
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
