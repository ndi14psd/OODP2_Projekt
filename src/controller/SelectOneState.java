package controller;

import java.util.Optional;

import model.DrawableShape;
import shape.Vertex;

final class SelectOneState implements DrawPanelState {

	private final PositionHandler handler;
	private final DrawPanelController controller;

	SelectOneState(DrawPanelController controller, PositionHandler handler) {
		this.handler = handler;
		this.controller = controller;
	}

	@Override
	public void leftMouseButtonPressed(int x, int y) {
		handler.deselectAll();

		Vertex pressed = Vertex.at(x, y);
		handler.setLastDragPoint(pressed);
		handler.setLastPressPoint(pressed);
		handler.setPressedShape(pressed);

		handler.lastPressedToggleSelected();
	}

	@Override
	public void leftMouseButtonReleased(int x, int y) {
		handler.deselectAll();
		handler.getPressedShape().ifPresent(shape -> {
			Vertex current = Vertex.at(x, y);
			Vertex lastPress = handler.getLastPressPoint().orElse(current);
			Vertex distance = current.subtract(lastPress);
			controller.addMovementToHistory(shape, distance);
		});
	}

	@Override
	public void mouseDragged(int x, int y) {
		Vertex currentPoint = Vertex.at(x, y);
		handler.moveAllSelected(currentPoint);
	}

	@Override
	public void leftMouseButtonClicked(int x, int y) {
	}

	@Override
	public void mouseWheelScrolled(int x, int y) {

	}

	@Override
	public void stateModifierKeyPressed() {
		controller.setState(new SelectManyState(controller, handler));
	}

	@Override
	public void stateModifierKeyReleased() {
	}

	@Override
	public void mouseMoved(int x, int y) {
	}
}
