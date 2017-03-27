package model;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.List;

import drawable.Drawable;
import drawable.ShapeDrawer;
import shape.Circle;
import shape.Shape;
import shape.ShapeVisitor;
import shape.Vertex;

public final class DrawableShape implements Drawable, Shape, Cloneable {
	private static final long serialVersionUID = -5101707247175075539L;
	
	private Shape shape;
    private double strokeWidth;
    private Color color;
    private boolean isSelected;

    public DrawableShape(Shape shape) {
        this.shape = shape;
        strokeWidth = 5;
        color = Color.black;
        isSelected = false;
    }
    
    @Override
    public DrawableShape clone() {
    	DrawableShape clone =  new DrawableShape(shape);
    	clone.color = this.color;
    	clone.strokeWidth = this.strokeWidth;
    	return clone;
    }
    
	public void setInnerShape(Shape shape) {
    	this.shape = shape;
    }
    
    public Shape getInnerShape() {
    	return shape;
    }
    
	public void setColor(Color color) {
        this.color = color;
    }
    
	public void setSelected(boolean isSelected) {
        this.isSelected = isSelected;
    }

	public boolean isSelected() {
        return isSelected;
    }

    public DrawableShape setCenter(Vertex distance) {
        shape = shape.setCenter(distance);
        return this;
    }

    @Override
    public void draw(Graphics graphics) {
        Graphics2D g2 = (Graphics2D) graphics;
        g2.setStroke(new BasicStroke((float) strokeWidth));
        graphics.setColor(isSelected? Color.CYAN : color);
        ShapeDrawer.draw(graphics, shape);
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

    public double getStrokeWidth() {
		return strokeWidth;
	}

	public void setStrokeWidth(double strokeWidth) {
		this.strokeWidth = strokeWidth;
	}
}
