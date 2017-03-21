package view;

import java.awt.Color;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import controller.ToolbarController;
import model.ShapeCreatorModel;

public class ToolbarPanel extends JPanel implements Observer {

	private List<JButton> buttons;
	private final ToolbarController controller;
	private ShapeCreatorModel model;

	public ToolbarPanel(ShapeCreatorModel model, ToolbarController controller) {
		this.model = model;
		this.controller = controller;
		this.setBackground(Color.DARK_GRAY);
		this.setMinimumSize(new Dimension(160, 544));
		this.setPreferredSize(new Dimension(160, 544));

		buttons = new ArrayList<>();
		
		model.addObserver(this);
		updateButtons();
	}

	@Override
	public void update(Observable o, Object arg) {
		updateButtons();
	}

	public void updateButtons() {
		for (JButton jButton : buttons) {
			this.remove(jButton);
		}
		buttons.add(getDefaultButton());

		JButton save = createSaveButton();
		buttons.add(save);

		for (String name : model.getShapeNames()) {
			buttons.add(getCreationButton(name));
		}

		buttons.forEach(this::add);
	}
	
	private JButton createSaveButton() {
		JButton saveButton = new JButton("Save selected");
		saveButton.addActionListener(action -> {
			controller.saveSelected(JOptionPane.showInputDialog("Enter name", "Custom Shape"));
		});
		return saveButton;
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
