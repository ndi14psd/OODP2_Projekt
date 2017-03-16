package model;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import shape.Circle;
import shape.ShapeComposite;
import shape.ShapeVisitor;

class AttributeCreator implements ShapeVisitor {

	private final List<Attribute> attributes;
	private ShapeModel model;
	private DrawableShape shape;

	private AttributeCreator(ShapeModel model, DrawableShape shape) {
		this.model = model;
		this.shape = shape;
		attributes = new ArrayList<>();
	}

	static List<Attribute> create(ShapeModel model, DrawableShape shape) {
		AttributeCreator creator = new AttributeCreator(model, shape);
		if(shape != null) {
			shape.accept(creator);			
		}

		return creator.getAttributes();
	}

	private List<Attribute> getAttributes() {
		return attributes;
	}
	
	@Override
	public void visit(ShapeComposite composite) {
		System.out.println("neeej");
	}

	@Override
	public void visit(Circle circle) {
		Consumer<String> radiusUpdater = s -> {
			double newRadius = Double.valueOf(s);
			Circle newCircle = Circle.get(circle.getCenter(), newRadius);
			model.updateShape(shape, drawableShape -> drawableShape.setInnerShape(newCircle));
		};
		attributes.add(new Attribute(String.valueOf(circle.getRadius()), "Radius", radiusUpdater));
	}
	
	
}