package controller;

import java.util.List;

import model.DrawableShape;
import model.MainModel;
import model.ShapeFileWriter;
import shape.ShapeComposite;

public class ToolbarController {
	
	private final MainModel model;
	private final DrawPanelController drawPanelController;
	private final CommandHistory history;

	ToolbarController(MainModel model, MainController mainController) {
		this.drawPanelController = mainController.getDrawPanelController();
		this.model = model;
		this.history = mainController.getHistory();
	}

	public void setCreationMode(String shapeName) {
		drawPanelController.setState(new ShapeCreationState(model, history, shapeName));
	}
	
	public void setDefaultMode() {
		drawPanelController.setState(drawPanelController.getDefaultState());
	}
	
	public void saveSelected(String shapeName) {
		List<DrawableShape> selected = drawPanelController.getPositionHandler().getSelected();
		ShapeComposite shape = new ShapeComposite();
		for (DrawableShape drawableShape : selected) {
			shape.addShape(drawableShape);
		}
		ShapeFileWriter.writeShapeToFile(shapeName, new DrawableShape(shape));
		model.getShapeMakerModel().reinitialize();
	}


}
