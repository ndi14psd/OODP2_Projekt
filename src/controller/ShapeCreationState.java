package controller;

import controller.command.AddShapeCommand;
import controller.command.Command;
import model.DrawableShape;
import model.MainModel;
import shape.Vertex;

public class ShapeCreationState implements DrawPanelState {

	private final MainModel model;
	private final String shapeName;
	private final CommandHistory history;

	public ShapeCreationState(MainModel model, CommandHistory history, String shapeName) {
		this.model = model;
		this.history = history;
		this.shapeName = shapeName;
	}

	@Override
	public void leftMouseButtonPressed(int x, int y) {
		Vertex currentPosition = Vertex.at(x, y);
		DrawableShape shape = model.getShapeMakerModel().getShape(shapeName, currentPosition);
		Command added = new AddShapeCommand(model.getShapeModel(), shape);
		added.execute();
		history.addToHistory(added);
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
