package controller;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import controller.command.Command;
import controller.command.CommandComposite;
import controller.command.DeleteShape;
import controller.command.MoveShape;
import model.DrawableShape;
import model.ShapeModel;
import model.ShapePropertyModel;
import shape.Vertex;

public final class DrawPanelController {

    private final ShapeModel shapeModel;
    private final PositionHandler handler;
    private final CommandHistory history;
    private DrawPanelState state;
	private ShapePropertyModel optionModel;

    DrawPanelController(ShapeModel shapeModel, ShapePropertyModel optionModel, CommandHistory history) {
		this.shapeModel = shapeModel;
		this.optionModel = optionModel; 
        handler = new PositionHandler(shapeModel);
        state = new SelectOneState(this, handler);
        this.history = history;
    }

    void addMovementToHistory(List<DrawableShape> shapes, Vertex distance) {
        if(distance != Vertex.at(0, 0)) {
            List<Command> commands = shapes.stream()
                    .map(s ->  new MoveShape(shapeModel, s, distance))
                    .collect(Collectors.toList());
            history.addToHistory(new CommandComposite(commands));
        }
    }

    void addMovementToHistory(DrawableShape shape, Vertex distance) {
        addMovementToHistory(Collections.singletonList(shape), distance);
    }
    
    public void deleteSelected() {
    	List<DrawableShape> selected = handler.getSelected();
    	if(!selected.isEmpty()) {
    		Command deleteSelected = new DeleteShape(shapeModel, selected);
    		deleteSelected.execute();
    		history.addToHistory(deleteSelected);    		
    	}
    }
    
    DrawPanelState getDefaultState() {
    	return new SelectOneState(this, handler);
    }
    
    PositionHandler getPositionHandler() {
    	return handler;
    }

    public void redo() {
        history.redoLast();
    }

    public void undo() {
        history.undoLast();
    }

    void setState(DrawPanelState state) {
        this.state = state;
    }

    public void leftMouseButtonPressed(int x, int y) {
        state.leftMouseButtonPressed(x, y);
    }

    public void leftMouseButtonReleased(int x, int y) {
        state.leftMouseButtonReleased(x, y);
    }

    public void mouseDragged(int x, int y) {
        state.mouseDragged(x, y);
    }

    public void leftMouseButtonClicked(int x, int y) {
        state.leftMouseButtonClicked(x, y);
    }

    public void mouseWheelScrolled(int x, int y) {
        state.mouseWheelScrolled(x, y);
    }

    public void stateModifierKeyPressed() {
        state.stateModifierKeyPressed();
    }

    public void stateModifierKeyReleased() {
        state.stateModifierKeyReleased();
    }

	public void mouseMoved(int x, int y) {
		state.mouseMoved(x, y);
	}

	public void rightMouseButtonPressed(int x, int y) {
		DrawableShape shape = handler.shapeAtPoint(Vertex.at(x, y)).orElse(null);
		optionModel.setShape(shape);
	}
}
