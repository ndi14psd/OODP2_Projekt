package controller;

import model.ShapeModel;

public class ToolbarController {
	
	private final ShapeModel model;
	private final DrawPanelController drawPanelController;
	private final CommandHistory history;

	ToolbarController(MainController mainController) {
		this.drawPanelController = mainController.getDrawPanelController();
		this.model = mainController.getShapeModel();
		this.history = mainController.getHistory();
	}

	public void setCreationMode(String shapeName) {
		drawPanelController.setState(new ShapeCreationState(model, history, shapeName));
	}
	
	public void setDefaultMode() {
		drawPanelController.setState(drawPanelController.getDefaultState());
	}


}
