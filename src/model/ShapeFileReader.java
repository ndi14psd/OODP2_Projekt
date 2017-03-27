package model;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.HashMap;
import java.util.Map;

public final class ShapeFileReader {

	private final Map<String, DrawableShape> shapes;

	private ShapeFileReader(String directoryName) {
		shapes = new HashMap<>();
		initializeShapeMakers(directoryName);
	}

	public static Map<String, DrawableShape> readDirectory(String directoryName) {
		return new ShapeFileReader(directoryName).shapes;
	}

	private void initializeShapeMakers(String directoryName) {
		File directory = new File(directoryName);
		if (directory.isDirectory()) {
			for (File file : directory.listFiles()) {
				if(file.getName().contains(".ser")) {
					readShapeObject(file);					
				}
			}
		}
	}

	public void readShapeObject(File file) {
		try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(file))) {
			String shapeName = file.getName();
			shapes.put(shapeName, (DrawableShape) in.readObject());
		} catch (IOException | ClassNotFoundException e) {
			throw new RuntimeException(e);
		}
	}

}
