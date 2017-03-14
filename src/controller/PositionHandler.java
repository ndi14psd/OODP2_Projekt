package controller;

import model.DrawableShape;
import model.ShapeModel;
import shape.Vertex;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

final class PositionHandler {

    private final ShapeModel model;

    private Vertex lastDragPoint;
    private Vertex lastPressPoint;
    private DrawableShape pressedShape;

    PositionHandler(ShapeModel model) {
        this.model = model;

        lastDragPoint = null;
        lastPressPoint = null;
        pressedShape = null;
    }

    List<DrawableShape> getSelected() {
        return model.getShapes().stream()
                .filter(DrawableShape::isSelected)
                .collect(Collectors.toList());
    }

    Optional<DrawableShape> getPressedShape() {
        return Optional.ofNullable(pressedShape);
    }

    Optional<Vertex> getLastDragPoint() {
        return Optional.ofNullable(lastDragPoint);
    }

    Optional<Vertex> getLastPressPoint() {
        return Optional.ofNullable(lastPressPoint);
    }

    void setLastDragPoint(Vertex v) {
        lastDragPoint = v;
    }

    void setLastPressPoint(Vertex v) {
        lastPressPoint = v;
    }

    void setPressedShape(Vertex pressedPoint) {
        pressedShape = model.getShapes().stream()
                .filter(shape -> shape.hasPoint(pressedPoint))
                .findFirst().orElse(null);
    }

    private void setDeselected(DrawableShape shape) {
        if (model.getShapes().contains(shape)) {
            shape.setSelected(false);
        }
    }

    void deselectAll() {
        for (DrawableShape drawableShape : getSelected()) {
            setDeselected(drawableShape);
        }
    }

    void lastPressedToggleSelected() {
        getPressedShape().ifPresent(shape -> shape.setSelected(!shape.isSelected()));
    }

    void moveAllSelected(Vertex currentPoint) {
        Vertex lastDragPoint = getLastDragPoint().orElse(currentPoint);
        Vertex distance = currentPoint.subtract(lastDragPoint);
        moveShapes(getSelected(), distance);
        setLastDragPoint(currentPoint);
    }

    private void moveShapes(List<DrawableShape> shapes, Vertex distance) {
        for (DrawableShape shape : shapes) {
            model.updateShape(shape, s -> s.moveCenter(distance));
        }
    }
}
