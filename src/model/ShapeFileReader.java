package model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import shape.Circle;
import shape.Shape;
import shape.ShapeComposite;
import shape.Vertex;

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
		if(directory.isDirectory()) {
			for (File file : directory.listFiles()) {
				String shapeName = file.getName().substring(0, file.getName().lastIndexOf('.'));
				shapeMakers.put(shapeName, readShapeFromFile(file));
			}			
		}
	}
	
	public DrawableShape getShapeMaker(String shapeName) {
		return shapeMakers.get(shapeName);
	}
	
	private DrawableShape readShapeFromFile(File file) {
		try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(file))) {
			return (DrawableShape) in.readObject();
		} catch (IOException | ClassNotFoundException e) {
			throw new RuntimeException(e);
		} 
	}

}
