package shape;

import java.util.List;

public class Square implements Shape {
	
	private final Rectangle rectangle;
	private final double side;

	public Square(Vertex center, double side) {
		this.side = side;
		this.rectangle = new Rectangle(center, side, side);
	}
	
	public double getSide() {
		return side;
	}
	
	@Override
	public List<Vertex> getVertices() {
		return rectangle.getVertices();
	}

	public Vertex bottomLeft() {
		return rectangle.bottomLeft();
	}

	public Vertex bottomRight() {
		return rectangle.bottomRight();
	}

	public Vertex topRight() {
		return rectangle.topRight();
	}

	public Vertex topLeft() {
		return rectangle.topLeft();
	}

	@Override
	public boolean hasPoint(Vertex vertex) {
		return rectangle.hasPoint(vertex);
	}

	@Override
	public Vertex getCenter() {
		return rectangle.getCenter();
	}

	@Override
	public Shape setCenter(Vertex vertex) {
		return new Square(vertex, side);
	}

	@Override
	public void accept(ShapeVisitor visitor) {
		visitor.visit(this);
	}

}
