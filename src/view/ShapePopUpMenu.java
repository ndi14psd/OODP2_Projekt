package view;

import java.awt.Color;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JColorChooser;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;

import controller.ShapeOptionController;
import model.ShapePropertyModel;

class ShapePopUpMenu extends JPopupMenu implements Observer {

	private ShapePropertyModel model;
	private ShapeOptionController controller;

	public ShapePopUpMenu(ShapePropertyModel model, ShapeOptionController controller) {
		this.model = model;
		this.controller = controller;
		model.addObserver(this);
	}

	@Override
	public void update(Observable o, Object arg) {
		this.removeAll();

		if (model.getShape().isPresent()) {

			JMenuItem colorItem = new JMenuItem("Change color");
			colorItem.addActionListener(action -> {
				Color c = JColorChooser.showDialog(this, "Choose Color", Color.BLACK);
				controller.setShapeColor(c);
			});
			add(colorItem);

			JMenuItem strokeItem = new JMenuItem("Change Stroke width");
			strokeItem.addActionListener(action -> {
				String input = JOptionPane.showInputDialog("Choose new stroke width");
				controller.setShapeStrokeWidth(input);
			});
			add(strokeItem);

			for (String option : model.getShapeProperties()) {
				JMenuItem item = new JMenuItem(option);
				item.addActionListener(action -> {
					String input = JOptionPane.showInputDialog("Change value");
					controller.changeShapeProperty(option, input);
				});
				add(item);
			}
		}
	}

}