package model;

import java.awt.Color;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Function;

import shape.Circle;
import shape.Rectangle;
import shape.Shape;
import shape.ShapeComposite;
import shape.ShapeVisitor;
import shape.Square;

class ShapePropertyCreator implements ShapeVisitor {

	private DrawableShape shape;
	
	private final Map<String, Consumer<Double>> options;
	
	private ShapePropertyCreator(DrawableShape shape) {
		this.shape = shape;
		options = new HashMap<>();
	}

	static Map<String, Consumer<Double>> get(DrawableShape shape) {
		ShapePropertyCreator creator = new ShapePropertyCreator(shape);
		if(shape != null) {
			shape.accept(creator);			
		}
		
		return creator.options;
	}
	
	@Override
	public void visit(ShapeComposite composite) {
	}

	@Override
	public void visit(Circle circle) {
		options.put("Radius", input -> shape.setInnerShape(Circle.get(circle.getCenter(), input)));
	}

	@Override
	public void visit(Square square) {
		options.put("Side", input -> shape.setInnerShape(new Square(square.getCenter(), input)));
	}

	@Override
	public void visit(Rectangle rectangle) {
		options.put("Width", input -> shape.setInnerShape(new Rectangle(shape.getCenter(), input, rectangle.getHeight())));
		options.put("Height", input -> shape.setInnerShape(new Rectangle(shape.getCenter(), rectangle.getWidth(), input)));
	}
	
	
}