package controller;

import controller.command.Command;
import controller.command.CommandComposite;
import controller.command.MoveCommand;
import model.ShapeModel;
import model.DrawableShape;
import shape.Vertex;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public final class DrawPanelController {

    private final ShapeModel model;
    private final PositionHandler handler;
    private final CommandHistory history;
    private DrawPanelState state;

    public DrawPanelController(ShapeModel model) {
        this.model = model;
        handler = new PositionHandler(model);
        state = new DefaultState(this, handler);
        history = new CommandHistory();
    }

    void addMovementToHistory(List<DrawableShape> shapes, Vertex distance) {
        if(distance != Vertex.at(0, 0)) {
            List<Command> commands = shapes.stream()
                    .map(s ->  new MoveCommand(model, s, distance))
                    .collect(Collectors.toList());
            history.addToHistory(new CommandComposite(commands));
        }
    }

    void addMovementToHistory(DrawableShape shape, Vertex distance) {
        addMovementToHistory(Collections.singletonList(shape), distance);
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
}
