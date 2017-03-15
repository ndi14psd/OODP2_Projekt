package controller.command;

import model.DrawableShape;
import model.ShapeModel;

public class AddShapeCommand implements Command {

	private final ShapeModel model;
	private final DrawableShape shape;

	public AddShapeCommand(ShapeModel model, DrawableShape shape) {
		this.model = model;
		this.shape = shape;
	}
	
	@Override
	public void execute() {
		model.addShape(shape);
	}

	@Override
	public void undo() {
		model.removeShape(shape);
	}

}
