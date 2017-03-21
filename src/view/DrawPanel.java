package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JPanel;

import controller.DrawPanelController;
import drawable.Drawable;
import model.ShapeModel;

public class DrawPanel extends JPanel implements Observer {

	private final ShapeModel model;
	public static final Dimension SIZE = new Dimension(800, 544);

	public DrawPanel(ShapeModel model, DrawPanelController controller) {
		this.model = model;
		this.setPreferredSize(SIZE);
		this.setMinimumSize(SIZE);
		this.setLayout(new BorderLayout());

		model.addObserver(this);

		new DrawPanelKeyHandler(controller, this);
		DrawPanelMouseHandler mouseHandler = new DrawPanelMouseHandler(this, controller);
		addMouseHandler(mouseHandler);
	}
	
	private void addMouseHandler(DrawPanelMouseHandler mouseHandler) {
		addMouseListener(mouseHandler);
		addMouseMotionListener(mouseHandler);
		addMouseWheelListener(mouseHandler);
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		for (Drawable drawable : model.getShapes()) {
			drawable.draw(g);
		}
	}

	@Override
	public void update(Observable o, Object arg) {
		repaint();
	}
}
