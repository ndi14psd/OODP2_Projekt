package controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import controller.command.AddShapeCommand;
import controller.command.Command;
import model.DrawableShape;
import model.ShapeModel;
import shape.Circle;
import shape.Shape;
import shape.ShapeComposite;
import shape.ShapeFactory;
import shape.Vertex;

public class ShapeCreationState implements DrawPanelState {

	public final static Map<String, Function<Vertex, DrawableShape>> SHAPE_MAKERS = new HashMap<>();

	static {
		SHAPE_MAKERS.put("circle", center -> new DrawableShape(ShapeFactory.getCircle(center, 50)));
	}

	private final ShapeModel model;
	private final String shapeName;
	private final CommandHistory history;

	public ShapeCreationState(ShapeModel model, CommandHistory history, String shapeName) {
		this.model = model;
		this.history = history;
		this.shapeName = shapeName.toLowerCase();
	}

	@Override
	public void leftMouseButtonPressed(int x, int y) {
		Vertex currentPosition = Vertex.at(x, y);
		Shape shape = SHAPE_MAKERS.get(shapeName).apply(currentPosition);
		DrawableShape drawableShape = new DrawableShape(shape);
		Command added = new AddShapeCommand(model, drawableShape);
		added.execute();
		history.addToHistory(added);
	}

	@Override
	public void rightMouseButtonPressed(int x, int y) {
	}

	@Override
	public void leftMouseButtonReleased(int x, int y) {
	}

	@Override
	public void mouseDragged(int x, int y) {
	}

	@Override
	public void leftMouseButtonClicked(int x, int y) {
	}

	@Override
	public void mouseWheelScrolled(int x, int y) {
	}

	@Override
	public void stateModifierKeyPressed() {
	}

	@Override
	public void stateModifierKeyReleased() {
	}

	@Override
	public void mouseMoved(int x, int y) {
	}

}
