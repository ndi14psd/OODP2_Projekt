package view;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;

import javax.swing.SwingUtilities;

import controller.DrawPanelController;

public class DrawPanelMouseHandler extends MouseAdapter {

	private final DrawPanel panel;
	private final DrawPanelController controller;

	public DrawPanelMouseHandler(DrawPanel panel, DrawPanelController controller) {
		this.panel = panel;
		this.controller = controller;
	}

	@Override
	public void mousePressed(MouseEvent e) {
		if (SwingUtilities.isLeftMouseButton(e)) {
			controller.leftMouseButtonPressed(e.getX(), e.getY());
			panel.repaint();
		} else if (SwingUtilities.isRightMouseButton(e)) {
			controller.rightMouseButtonPressed(e.getX(), e.getY());
		}
	}

	

	@Override
	public void mouseMoved(MouseEvent e) {
		controller.mouseMoved(e.getX(), e.getY());
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		controller.leftMouseButtonReleased(e.getX(), e.getY());
		panel.repaint();
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		controller.leftMouseButtonClicked(e.getX(), e.getY());
		panel.repaint();
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		controller.mouseDragged(e.getX(), e.getY());
		panel.repaint();
	}

	@Override
	public void mouseWheelMoved(MouseWheelEvent e) {
		controller.mouseWheelScrolled(e.getX(), e.getY());
		panel.repaint();
	}
}
