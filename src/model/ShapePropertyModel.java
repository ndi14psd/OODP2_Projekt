package model;

import java.util.HashMap;
import java.util.Map;
import java.util.Observable;
import java.util.Optional;
import java.util.Set;
import java.util.function.Function;

import shape.Shape;

public class ShapePropertyModel extends Observable {

	private DrawableShape currentShape;
	private Map<String, Function<Double, Shape>> shapeProperties;

	public ShapePropertyModel() {
		currentShape = null;
		shapeProperties = new HashMap<>();
	}

	public Shape getUpdatedProperty(String propertyName, double value) {
		return shapeProperties.get(propertyName).apply(value);
	}

	public void setShape(DrawableShape shape) {
		currentShape = shape;
		shapeProperties = ShapePropertyCreator.get(shape);
		updateObservers();
	}

	public Set<String> getShapeProperties() {
		return shapeProperties.keySet();
	}

	private void updateObservers() {
		super.setChanged();
		super.notifyObservers();
	}

	public Optional<DrawableShape> getShape() {
		return Optional.ofNullable(currentShape);
	}
}
