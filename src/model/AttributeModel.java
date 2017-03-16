package model;

import java.awt.Color;
import java.util.List;
import java.util.Observable;
import java.util.Optional;

public class AttributeModel extends Observable {
	
	private final ShapeModel shapeModel;
	private DrawableShape currentShape;

	public AttributeModel(ShapeModel shapeModel) {
		this.shapeModel = shapeModel;
		currentShape = null;
	}
		
	public void setShape(DrawableShape shape) {
		currentShape = shape;
		updateObservers();
	}
	
	public Attribute getColorAttribute() {
		return new Attribute(currentShape.getColor().toString(), "Color",
				rgbString -> currentShape.setColor(new Color(Integer.parseInt(rgbString))));
	}
	
	public Attribute getBorderWidthAttribute() {
		return new Attribute(Double.toString(currentShape.getStrokeWidth()), "Stroke Width",
				strokeWidthString -> currentShape.setStrokeWidth(Double.parseDouble(strokeWidthString)));
	}
	
	public List<Attribute> getAttributes() {
		return AttributeCreator.create(shapeModel, currentShape);
	}
	
	private void updateObservers() {
		super.setChanged();
		super.notifyObservers();
	}

	public Optional<DrawableShape> getShape() {
		return Optional.ofNullable(currentShape);
	}
}
