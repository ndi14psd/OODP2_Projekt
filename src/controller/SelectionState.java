package controller;

import shape.Vertex;

class SelectionState implements DrawPanelState {

    private final DrawPanelController controller;
    private final PositionHandler handler;

    SelectionState(DrawPanelController controller, PositionHandler handler) {
        this.controller = controller;
        this.handler = handler;
    }

    @Override
    public void leftMouseButtonPressed(int x, int y) {
        Vertex pressed = Vertex.at(x, y);
        handler.setLastDragPoint(pressed);
        handler.setLastPressPoint(pressed);
        handler.setPressedShape(pressed);
    }

    @Override
    public void leftMouseButtonReleased(int x, int y) {
        Vertex current = Vertex.at(x, y);
        Vertex lastPress = handler.getLastPressPoint().orElse(current);
        Vertex distance = current.subtract(lastPress);
        controller.addMovementToHistory(handler.getSelected(), distance);
    }

    @Override
    public void mouseDragged(int x, int y) {
        handler.getPressedShape().ifPresent(shape -> {
            if(handler.getSelected().contains(shape)) {
                Vertex currentPoint = Vertex.at(x, y);
                handler.moveAllSelected(currentPoint);
            }
        });
    }

    @Override
    public void leftMouseButtonClicked(int x, int y) {
        handler.lastPressedToggleSelected();
    }

    @Override
    public void mouseWheelScrolled(int x, int y) {

    }

    @Override
    public void stateModifierKeyPressed() {

    }

    @Override
    public void stateModifierKeyReleased() {
        controller.setState(new DefaultState(controller, handler));
    }
}
