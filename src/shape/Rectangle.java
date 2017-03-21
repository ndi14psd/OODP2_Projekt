package shape;

import java.util.Arrays;
import java.util.List;

public final class Rectangle implements Shape {
	

	private final Vertex center;
	private final double width;
	private final double height;

	public Rectangle(Vertex center, double width, double height) {
		this.center = center;
		this.width = width;
		this.height = height;
	}
	
	public double getWidth() {
		return width;
	}
	
	public double getHeight() {
		return height;
	}

	@Override
	public List<Vertex> getVertices() {
		return Arrays.asList(topLeft(), topRight(), bottomRight(), bottomLeft());
	}

	public Vertex bottomLeft() {
		return bottomRight().subtract(Vertex.at(width, 0));
	}

	public Vertex bottomRight() {
		return topRight().subtract(Vertex.at(0, height));
	}

	public Vertex topRight() {
		return topLeft().add(Vertex.at(width, 0));
	}

	public Vertex topLeft() {
		return Vertex.at(center.x() - width / 2, center.y() + height / 2);
	}

	@Override
	public boolean hasPoint(Vertex vertex) {
		return vertex.x() >= topLeft().x() && vertex.x() <= topRight().x()
				&& vertex.y() >= bottomLeft().y() && vertex.y() <= topRight().y();
	}

	@Override
	public Vertex getCenter() {
		return center;
	}

	@Override
	public Shape setCenter(Vertex vertex) {
		return new Rectangle(vertex, width, height);
	}

	@Override
	public void accept(ShapeVisitor visitor) {
		visitor.visit(this);
	}

}
