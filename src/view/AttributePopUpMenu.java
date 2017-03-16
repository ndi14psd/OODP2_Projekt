package view;

import java.awt.Color;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JColorChooser;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;

import controller.AttributeController;
import model.Attribute;
import model.AttributeModel;

class AttributePopUpMenu extends JPopupMenu implements Observer {

	private AttributeModel model;
	private AttributeController controller;
	
	public AttributePopUpMenu(AttributeModel model, AttributeController controller) {
		this.model = model;
		this.controller = controller;
		model.addObserver(this);
	}

	@Override
	public void update(Observable o, Object arg) {
		this.removeAll();
		if (model.getShape().isPresent()) {
			add(createColorMenuItem());
			add(createMenuItem(model.getBorderWidthAttribute()));
			for (Attribute attribute : model.getAttributes()) {
				add(createMenuItem(attribute));
			}
		}
	}

	private JMenuItem createColorMenuItem() {
		JMenuItem colorItem = new JMenuItem("Color");
		colorItem.addActionListener(action -> {
			Color c = JColorChooser.showDialog(this, "Choose Color", Color.BLACK);
			controller.setColor(c);
		});
		return colorItem;
	}

	private JMenuItem createMenuItem(Attribute attribute) {
		JMenuItem item = new JMenuItem(attribute.getName());
		item.addActionListener(createAttributeActionListener(attribute));
		return item;
	}

	private ActionListener createAttributeActionListener(Attribute attribute) {
		return action -> {
			String answer = JOptionPane.showInputDialog("Change value", attribute.getValue());
			controller.updateAttribute(attribute, answer);
		};
	}
}