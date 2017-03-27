package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Observable;
import java.util.function.Function;

import shape.Circle;
import shape.Rectangle;
import shape.Square;
import shape.Vertex;

public class ShapeCreatorModel extends Observable {

	private final Map<String, Function<Vertex, DrawableShape>> defaultShapeMakers;
	private final Map<String, Function<Vertex, DrawableShape>> customShapeMakers;

	public ShapeCreatorModel() {
		defaultShapeMakers = new HashMap<>();
		customShapeMakers = new HashMap<>();
		reinitialize();
	}
	
	private void updateObservers() {
		super.setChanged();
		super.notifyObservers();
	}
	
	private void reinitializeShapes() {
		defaultShapeMakers.clear();
		customShapeMakers.clear();
		initializeDefault();
		initializeFromFiles();
	}
	
	public void reinitialize() {
		reinitializeShapes();
		updateObservers();
	}
	
	public DrawableShape getShape(String shapeName, Vertex position) {
		DrawableShape shape = defaultShapeMakers.getOrDefault(shapeName, customShapeMakers.get(shapeName)).apply(position);
		reinitializeShapes();
		return shape;
	}
	
	public List<String> getShapeNames() {
		List<String> allCreatorNames = new ArrayList<>();
		allCreatorNames.addAll(defaultShapeMakers.keySet());
		allCreatorNames.addAll(customShapeMakers.keySet());
		return allCreatorNames;
	}
	
	private void initializeDefault() {
		defaultShapeMakers.put("Circle", center -> new DrawableShape(Circle.get(center, 50)));
		defaultShapeMakers.put("Square", center -> new DrawableShape(new Square(center, 50)));
		defaultShapeMakers.put("Rectangle", center -> new DrawableShape(new Rectangle(center, 75, 50)));
	}
	
	private void initializeFromFiles() {
		Map<String, DrawableShape> fromFiles = ShapeFileReader.readDirectory("shapes/");
		
		for (Entry<String, DrawableShape> entry: fromFiles.entrySet()) {
			customShapeMakers.put(entry.getKey(), center -> entry.getValue().clone().setCenter(center));
		}
	}
}
