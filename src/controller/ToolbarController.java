package controller;

import model.MainModel;
import model.ShapeModel;

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


}
