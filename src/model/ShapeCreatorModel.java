package model;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Observable;
import java.util.Set;
import java.util.function.Function;

import shape.Circle;
import shape.Rectangle;
import shape.Square;
import shape.Vertex;

public class ShapeCreatorModel extends Observable {

	private final Map<String, Function<Vertex, DrawableShape>> shapeMakers;

	public ShapeCreatorModel() {
		shapeMakers = new HashMap<>();
		reinitialize();
	}
	
	private void updateObservers() {
		super.setChanged();
		super.notifyObservers();
	}
	
	public void reinitialize() {
		shapeMakers.clear();
		initializeDefault();
		//initializeFromFiles();
		updateObservers();
	}
	
	public DrawableShape getShape(String shapeName, Vertex position) {
		return shapeMakers.get(shapeName).apply(position);
	}
	
	public Set<String> getShapeNames() {
		return shapeMakers.keySet(); 
	}
	
	private void initializeDefault() {
		shapeMakers.put("Circle", center -> new DrawableShape(Circle.get(center, 50)));
		shapeMakers.put("Square", center -> new DrawableShape(new Square(center, 50)));
		shapeMakers.put("Rectangle", center -> new DrawableShape(new Rectangle(center, 75, 50)));
	}
	
	private void initializeFromFiles() {
		Map<String, DrawableShape> fromFiles = ShapeFileReader.readDirectory("shapes/");
		
		for (Entry<String, DrawableShape> entry: fromFiles.entrySet()) {
			shapeMakers.putIfAbsent(entry.getKey(), center -> {
				DrawableShape shape = new DrawableShape(entry.getValue());
				return shape.setCenter(center);
			});
		}
	}
}
