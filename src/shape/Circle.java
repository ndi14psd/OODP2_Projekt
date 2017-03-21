package shape;

import java.util.Collections;
import java.util.List;

public final class Circle implements Shape {
	
	private static final long serialVersionUID = -8822679556704420763L;
	private Vertex center;
    private final double radius;

    private Circle(Vertex center, double radius) {
        this.center = center;
        this.radius = radius;
    }

    public static Circle get(Vertex center, double radius) {
        return new Circle(center, radius);
    }

    public double getRadius() {
        return radius;
    }
    
    @Override
    public List<Vertex> getVertices() {
    	return Collections.singletonList(getCenter());
    }

    @Override
    public Vertex getCenter() {
        return center;
    }

    public double getDiameter() {
        return radius * 2;
    }

    public double getArea() {
        return Math.PI * radius * radius;
    }

    @Override
    public boolean hasPoint(Vertex vertex) {
        double dx2 = Math.pow(vertex.x() - center.x(), 2);
        double dy2 = Math.pow(vertex.y() - center.y(), 2);

        return dx2 + dy2 < radius * radius;
    }

    @Override
    public Shape setCenter(Vertex vertex) {
        return get(vertex, this.radius);
    }

    @Override
    public void accept(ShapeVisitor visitor) {
        visitor.visit(this);
    }
}
