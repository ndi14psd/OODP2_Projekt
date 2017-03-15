package controller;

import model.ShapeModel;

public class MainController {
	
	private final ToolbarController toolbarController;
	private final DrawPanelController drawPanelController;
	private final ShapeModel model;
	private final CommandHistory history;
	
	public MainController(ShapeModel model) {
		this.model = model;
		history = new CommandHistory();
		drawPanelController = new DrawPanelController(this);
		toolbarController = new ToolbarController(this);
	}
	
	ShapeModel getShapeModel() {
		return model;
	}
	
	public ToolbarController getToolbarController() {
		return toolbarController;
	}
	
	public DrawPanelController getDrawPanelController() {
		return drawPanelController;
	}
	
	public void shapeCreationMode(String shapeName) {
		drawPanelController.setState(new ShapeCreationState(model, history, shapeName));
	}

	CommandHistory getHistory() {
		return history;
	}

}
