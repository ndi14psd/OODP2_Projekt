package drawable;

import java.awt.Graphics;

import shape.Circle;
import shape.Rectangle;
import shape.Shape;
import shape.ShapeComposite;
import shape.ShapeVisitor;
import shape.Square;

public final class ShapeDrawer implements ShapeVisitor {

    private final Graphics graphics;

    private ShapeDrawer(Graphics graphics) {
        this.graphics = graphics;
    }

    public static void draw(Graphics graphics, Shape shape) {
        shape.accept(new ShapeDrawer(graphics));
    }

    @Override
    public void visit(ShapeComposite composite) {
        for (Shape shape : composite.getList()) {
            shape.accept(this);
        }
    }

    @Override
    public void visit(Circle circle) {
        int diameter = (int) circle.getDiameter();
        int x = (int) (circle.getCenter().x() - circle.getRadius());
        int y = (int) (circle.getCenter().y() - circle.getRadius());
        graphics.drawOval(x, y, diameter, diameter);
    }

	@Override
	public void visit(Square square) {
        int x = (int) (square.getCenter().x() - square.getSide() / 2);
        int y = (int) (square.getCenter().y() - square.getSide() / 2);
        graphics.drawRect(x, y, (int) square.getSide(), (int) square.getSide());
	}

	@Override
	public void visit(Rectangle rectangle) {
		int x = (int) (rectangle.getCenter().x() - rectangle.getWidth() / 2);
        int y = (int) (rectangle.getCenter().y() - rectangle.getHeight() / 2);
        graphics.drawRect(x, y, (int) rectangle.getWidth(), (int) rectangle.getHeight());
	}

}
