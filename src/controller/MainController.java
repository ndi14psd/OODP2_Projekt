package controller;

import java.awt.Color;

import model.DrawableShape;
import model.MainModel;
import model.ShapeFileWriter;
import model.ShapeModel;
import shape.Circle;
import shape.ShapeComposite;
import shape.Vertex;

public class MainController {
	
	private final ToolbarController toolbarController;
	private final DrawPanelController drawPanelController;
	private final AttributeController attributeController;
	private final MainModel model;
	private final CommandHistory history;
	
	public MainController(MainModel model) {
		this.model = model;
		history = new CommandHistory();
		drawPanelController = new DrawPanelController(model.getShapeModel(), model.getAttributeModel(), history);
		toolbarController = new ToolbarController(model, this);
		attributeController = new AttributeController(model.getShapeModel(), model.getAttributeModel());
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
	
	public AttributeController getAttributeController() {
		return attributeController;
	}
	
	public void shapeCreationMode(String shapeName) {
		drawPanelController.setState(new ShapeCreationState(model, history, shapeName));
	}

	CommandHistory getHistory() {
		return history;
	}

}
