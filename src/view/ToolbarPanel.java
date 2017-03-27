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
	private JButton selectModeButton;
	private final ToolbarController controller;
	private ShapeCreatorModel model;
	private final DrawPanel drawPanel;

	public ToolbarPanel(ShapeCreatorModel model, ToolbarController controller, DrawPanel drawPanel) {
		this.model = model;
		this.controller = controller;
		this.drawPanel = drawPanel;
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
		removeAll();
		buttons.clear();
		
		buttons.add(createExportImageButton());
		buttons.add(createSaveButton());
		buttons.add(createSelectAllButton());
		buttons.add(getSelectModeButton());
		
		for (String name : model.getShapeNames()) {
			buttons.add(getCreationButton(name));
		}		
		
		for (JButton button : buttons) {
			add(button);
		}
		revalidate();
	}
	
	private JButton createSelectAllButton() {
		JButton button = new JButton("Select All");
		button.addActionListener(action -> controller.selectAll());
		return button;
	}
	
	private JButton createExportImageButton() {
		JButton exportImageButton = new JButton("Export image");
		exportImageButton.addActionListener(action -> {
			String fileName = JOptionPane.showInputDialog("Enter file name");
			controller.saveGraphicsAsImage(drawPanel.getWidth(), drawPanel.getHeight(), drawPanel::paintAll, fileName);
		});
		return exportImageButton;
	}

	private JButton createSaveButton() {
		JButton saveButton = new JButton("Save selected");
		saveButton.addActionListener(action -> {
			setEnabledAllButtons(false);
			String inputDialog = JOptionPane.showInputDialog("Enter name", "Custom Shape");
			controller.saveSelected(inputDialog);
			getSelectModeButton().doClick();
		});
		return saveButton;
	}

	private JButton getSelectModeButton() {
		if (selectModeButton == null) {
			selectModeButton = new JButton("Select Mode");
			selectModeButton.setEnabled(false);
			selectModeButton.addActionListener(action -> {
				setEnabledAllButtons(true);
				controller.setDefaultMode();
				selectModeButton.setEnabled(false);
			});
		}
		return selectModeButton;
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
