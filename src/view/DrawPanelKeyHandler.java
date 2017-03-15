package view;

import controller.DrawPanelController;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

class DrawPanelKeyHandler {

    private final DrawPanelController controller;
    private final InputMap inputMap;
    private final ActionMap actionMap;

    private boolean controlPressed;

    DrawPanelKeyHandler(DrawPanelController controller, DrawPanel panel) {
        this.controller = controller;
        controlPressed = false;
        inputMap = panel.getInputMap(JPanel.WHEN_IN_FOCUSED_WINDOW);
        actionMap = panel.getActionMap();

        addAction(KeyStroke.getKeyStroke("control CONTROL"), "ctrl pressed",
                new AbstractAction() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (!controlPressed) {
                            controller.stateModifierKeyPressed();
                            controlPressed = true;
                        }
                    }
                });


        addAction(KeyStroke.getKeyStroke("released CONTROL"), "ctrl released",
                new AbstractAction() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (controlPressed) {
                            controller.stateModifierKeyReleased();
                            controlPressed = false;
                        }
                    }
                });

        addAction(KeyStroke.getKeyStroke(KeyEvent.VK_Z, InputEvent.CTRL_DOWN_MASK), "undo",
                new AbstractAction() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        controller.undo();
                    }
                });

        addAction(KeyStroke.getKeyStroke(KeyEvent.VK_Y, InputEvent.CTRL_DOWN_MASK), "redo",
                new AbstractAction() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        controller.redo();
                    }
                });
        
        addAction(KeyStroke.getKeyStroke(KeyEvent.VK_DELETE, 0), "delete", 
        		new AbstractAction() {
					@Override
					public void actionPerformed(ActionEvent e) {
						controller.deleteSelected();
					}
				});
    }

    private void addAction(KeyStroke keyStroke, String actionName, Action action) {
        inputMap.put(keyStroke, actionName);
        actionMap.put(actionName, action);
    }
}
