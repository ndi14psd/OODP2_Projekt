package model;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public final class ShapeFileWriter {

	private ShapeFileWriter() {
	}

	public static void writeShapeToFile(String shapeName, DrawableShape shape) {
		try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("shapes/" + shapeName + ".ser"))) {
			out.writeObject(shape);
		} catch (IOException e) {
			throw new RuntimeException(e);
		} 
	}
}
