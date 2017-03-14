package view;

import javax.swing.*;

import controller.DrawPanelController;
import drawable.Drawable;
import model.*;

import java.awt.*;

public class DrawPanel extends JPanel implements AddedShapeObserver, RemovedShapeObserver, UpdatedShapeObserver {

    private final ShapeModel model;
    public static final Dimension SIZE = new Dimension(800, 544);

    public DrawPanel(ShapeModel model, DrawPanelController controller) {
        this.model = model;
        this.setPreferredSize(SIZE);
        this.setMinimumSize(SIZE);
        this.setLayout(new BorderLayout());

        subscribeToModel(model);

        new DrawPanelKeyHandler(controller, this);
        DrawPanelMouseHandler mouseHandler = new DrawPanelMouseHandler(this, controller);
        addMouseHandler(mouseHandler);
    }

    private void addMouseHandler(DrawPanelMouseHandler mouseHandler) {
        addMouseListener(mouseHandler);
        addMouseMotionListener(mouseHandler);
        addMouseWheelListener(mouseHandler);
    }

    private void subscribeToModel(ShapeModel model) {
        model.addAddedObserver(this);
        model.addRemovedObserver(this);
        model.addUpdatedObserver(this);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (Drawable drawable : model.getShapes()) {
			drawable.draw(g);
		}
    }

    @Override
    public void updateShapeAdded(DrawableShape shape) {
        repaint();
    }

    @Override
    public void updateShapeRemoved(DrawableShape shape) {
        repaint();
    }

    @Override
    public void updateShapesUpdated() {
        repaint();
    }
}
