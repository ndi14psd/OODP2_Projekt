package model;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.HashMap;
import java.util.Map;

public final class ShapeFileReader {

	private final Map<String, DrawableShape> shapeMakers;

	private ShapeFileReader(String directoryName) {
		shapeMakers = new HashMap<>();
		initializeShapeMakers(directoryName);
	}

	public static Map<String, DrawableShape> readDirectory(String directoryName) {
		return new ShapeFileReader(directoryName).shapeMakers;
	}

	private void initializeShapeMakers(String directoryName) {
		File directory = new File(directoryName);
		if (directory.isDirectory()) {
			for (File file : directory.listFiles()) {
				try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(file))) {
					String shapeName = file.getName();
					shapeMakers.put(shapeName, (DrawableShape) in.readObject());
				} catch (IOException | ClassNotFoundException e) {
					throw new RuntimeException(e);
				}
			}
		}
	}

	public DrawableShape getShapeMaker(String shapeName) {
		return shapeMakers.get(shapeName);
	}

}
