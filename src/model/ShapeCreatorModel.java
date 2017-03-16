package model;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.function.Function;

import shape.ShapeFactory;
import shape.Vertex;

public class ShapeCreatorModel {

	private final Map<String, Function<Vertex, DrawableShape>> shapeMakers;

	public ShapeCreatorModel() {
		shapeMakers = new HashMap<>();
		initializeDefault();
		//initializeFromFiles();
	}
	
	public DrawableShape getShape(String shapeName, Vertex position) {
		return shapeMakers.get(shapeName).apply(position);
	}
	
	public Set<String> getShapeNames() {
		return shapeMakers.keySet(); 
	}
	
	private void initializeDefault() {
		shapeMakers.put("Circle", center -> new DrawableShape(ShapeFactory.getCircle(center, 50)));
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
