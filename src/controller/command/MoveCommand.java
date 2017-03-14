package controller.command;

import model.DrawableShape;
import model.ShapeModel;
import shape.Vertex;

public class MoveCommand implements Command {

    private final ShapeModel model;
    private final DrawableShape shape;
    private final Vertex distance;

    public MoveCommand(ShapeModel model, DrawableShape shape, Vertex distance) {
        this.model = model;
        this.shape = shape;
        this.distance = distance;
    }

    @Override
    public void execute() {
        model.updateShape(shape, s -> s.moveCenter(distance));
    }

    @Override
    public void undo() {
        model.updateShape(shape, s -> s.moveCenter(distance.negative()));
    }
}
