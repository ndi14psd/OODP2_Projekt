package controller;

import model.MainModel;

public class MainController {
	
	private final ToolbarController toolbarController;
	private final DrawPanelController drawPanelController;
	private final ShapeOptionController attributeController;
	private final MainModel model;
	private final CommandHistory history;
	
	public MainController(MainModel model) {
		this.model = model;
		history = new CommandHistory();
		drawPanelController = new DrawPanelController(model.getShapeModel(), model.getAttributeModel(), history);
		toolbarController = new ToolbarController(model, this);
		attributeController = new ShapeOptionController(model.getShapeModel(), model.getAttributeModel());
	}
	
	MainModel getModel() {
		return model;
	}
	
	public ToolbarController getToolbarController() {
		return toolbarController;
	}
	
	public DrawPanelController getDrawPanelController() {
		return drawPanelController;
	}
	
	public ShapeOptionController getShapeOptionController() {
		return attributeController;
	}
	
	public void shapeCreationMode(String shapeName) {
		drawPanelController.setState(new ShapeCreationState(model, history, shapeName));
	}

	CommandHistory getHistory() {
		return history;
	}

}
