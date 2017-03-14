package view;

import controller.DrawPanelController;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;

public class DrawPanelMouseHandler extends MouseAdapter {

    private final DrawPanel panel;
    private final DrawPanelController controller;

    public DrawPanelMouseHandler(DrawPanel panel, DrawPanelController controller) {
        this.panel = panel;
        this.controller = controller;
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if(SwingUtilities.isLeftMouseButton(e)) {
            controller.leftMouseButtonPressed(e.getX(), e.getY());
            panel.repaint();
        }
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
