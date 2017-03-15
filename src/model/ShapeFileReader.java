package model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import shape.Shape;
import shape.Vertex;

public final class ShapeFileReader {
	
	private final Map<String, Function<Vertex, DrawableShape>> shapeMakers;
	
	public static void main(String[] args) {
		new ShapeFileReader("shapes/");
	}
	
	public ShapeFileReader(String directoryName) {
		shapeMakers = new HashMap<>();
		initializeShapeMakers(directoryName);
	}

	private void initializeShapeMakers(String directoryName) {
		File directory = new File(directoryName);
		for (File file : directory.listFiles()) {
			System.out.println(file.getAbsolutePath());
			shapeMakers.put(file.getName(), getFunction(readShapeFromFile(file)));
		}
	}
	
	public Function<Vertex, DrawableShape> getShapeMaker(String shapeName) {
		return shapeMakers.get(shapeName);
	}
	
	private Function<Vertex, DrawableShape> getFunction(Shape shape) {
		return vertex -> {
			Shape s = shape.setCenter(vertex);
			return new DrawableShape(s);
		};
	}
	
	
	private static Shape readShapeFromFile(File file) {
		try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(file))) {
			return (Shape) in.readObject();
		} catch (IOException | ClassNotFoundException e) {
			throw new RuntimeException(e);
		} 
	}

}
