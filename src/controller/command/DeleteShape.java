package controller.command;

import java.util.List;

import model.DrawableShape;
import model.ShapeModel;

public class DeleteShape implements Command {
	
	private final List<DrawableShape> shapes;
	private final ShapeModel model;
	
	public DeleteShape(ShapeModel model, List<DrawableShape> shapes) {
		this.model = model;
		this.shapes = shapes;
	}
	
	@Override
	public void execute() {
		for (DrawableShape shape : shapes) {
			model.removeShape(shape);
		}
	}

	@Override
	public void undo() {
		for (DrawableShape shape : shapes) {
			model.addShape(shape);
		}
	}

}
