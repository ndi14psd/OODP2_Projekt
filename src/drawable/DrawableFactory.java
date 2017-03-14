package drawable;

import shape.Circle;
import shape.Shape;
import shape.ShapeComposite;
import shape.ShapeVisitor;

import java.awt.*;

public final class DrawableFactory implements ShapeVisitor {

    private Drawable drawable;

    private final static int MARKED_POINT_SIZE = 50;

    private DrawableFactory(Shape shape) {
        shape.accept(this);
    }

    public static Drawable create(Shape shape) {
        return new DrawableFactory(shape).getDrawable();
    }

    public static Drawable createMarked(Shape shape) {
        return g -> {
            g.setColor(Color.CYAN);
            new DrawableFactory(shape).getDrawable().draw(g);
        };
    }

    private Drawable getDrawable() {
        return drawable;
    }

    private Drawable markPoints(Shape shape, int size) {
        return g -> {
            g.setColor(Color.RED);
            int x = (int) shape.getCenter().x() - size / 2;
            int y = (int) shape.getCenter().y() - size / 2;
            g.fillOval(x, y, size, size);
        };
    }

    @Override
    public void visit(ShapeComposite composite) {
        drawable = composite.getList().stream()
                .map(DrawableFactory::create)
                .reduce(Drawable::compose)
                .orElse(g -> {});
    }

    @Override
    public void visit(Circle circle) {
        int diameter = (int) circle.getDiameter();
        int x = (int) (circle.getCenter().x() - circle.getRadius());
        int y = (int) (circle.getCenter().y() - circle.getRadius());
        drawable = g -> g.drawOval(x, y, diameter, diameter);
    }

}
