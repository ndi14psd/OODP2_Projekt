package drawable;

import shape.Circle;
import shape.Shape;
import shape.ShapeComposite;
import shape.ShapeVisitor;

import java.awt.*;

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
            shape.accept(new ShapeDrawer(graphics));
        }
    }

    @Override
    public void visit(Circle circle) {
        int diameter = (int) circle.getDiameter();
        int x = (int) (circle.getCenter().x() - circle.getRadius());
        int y = (int) (circle.getCenter().y() - circle.getRadius());
        graphics.drawOval(x, y, diameter, diameter);
    }

}
