package view;

import javax.swing.*;

import controller.ToolbarController;
import model.ShapeFileReader;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class ToolbarPanel extends JPanel {
	
	private String currentShapeToolName;
	private final List<JButton> buttons;
	private final ToolbarController controller;

    public ToolbarPanel(ToolbarController controller) {
        this.controller = controller;
		this.setBackground(Color.DARK_GRAY);
        this.setMinimumSize(new Dimension(160, 544));
        this.setPreferredSize(new Dimension(160, 544));
        
        buttons = new ArrayList<>();
        buttons.add(getDefaultButton());
        buttons.add(getCreationButton("Circle"));
       /* for(String shapeName : ShapeFileReader.SHAPE_MAKERS.keySet()) {
        	buttons.add(getCreationButton(shapeName));
        }*/
        buttons.forEach(this::add);
    }
    
    private JButton getDefaultButton() {
    	JButton button = new JButton("Select Mode");
    	button.setEnabled(false);
    	button.addActionListener(action -> {
    		setEnabledAllButtons(true);
    		controller.setDefaultMode();
    		button.setEnabled(false);
    	});
    	return button;
    }
    
    private JButton getCreationButton(String shapeName) {
    	JButton button = new JButton(shapeName);
        button.addActionListener(action -> {
        	setEnabledAllButtons(true);
        	controller.setCreationMode(shapeName);
        	button.setEnabled(false);
        });
        return button;
    }
    
    private void setEnabledAllButtons(boolean enabled) {
    	for (JButton jButton : buttons) {
			jButton.setEnabled(enabled);
		}
    }
}
