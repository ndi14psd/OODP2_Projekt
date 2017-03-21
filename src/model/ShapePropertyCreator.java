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

	private ShapeModel model;
	private DrawableShape shape;
	
	private final Map<String, Function<Double, Shape>> options;
	
	private ShapePropertyCreator(DrawableShape shape) {
		this.model = model;
		this.shape = shape;
		options = new HashMap<>();
	}

	static Map<String, Function<Double, Shape>> get(DrawableShape shape) {
		ShapePropertyCreator creator = new ShapePropertyCreator(shape);
		shape.accept(creator);
		
		
		return creator.options;
	}
	
	@Override
	public void visit(ShapeComposite composite) {
	}

	@Override
	public void visit(Circle circle) {
		options.put("Radius", input -> Circle.get(circle.getCenter(), input));
	}

	@Override
	public void visit(Square square) {
		
	}

	@Override
	public void visit(Rectangle rectangle) {
		
	}
	
	
}