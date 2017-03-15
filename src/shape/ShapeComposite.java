package shape;

import java.util.*;
import java.util.List;
import java.util.function.Function;
import java.util.function.IntBinaryOperator;
import java.util.stream.Collectors;

public final class ShapeComposite implements Shape {

	private final List<Shape> shapes;

	public ShapeComposite() {
		this(new LinkedList<>());
	}

	private ShapeComposite(List<Shape> shapes) {
		this.shapes = shapes;
	}

	public List<Shape> getList() {
		return new LinkedList<>(shapes);
	}

	public boolean addShape(Shape shape) {
		return shapes.add(shape);
	}

	public Optional<Shape> removeShape(Shape shape) {
		if (shapes.contains(shape)) {
			shapes.remove(shape);
			return Optional.of(shape);
		}
		return Optional.empty();
	}

	@Override
	public List<Vertex> getVertices() {
		return shapes.stream().map(Shape::getVertices).flatMap(List::stream).collect(Collectors.toList());
	}

	@Override
	public boolean hasPoint(Vertex vertex) {
		return shapes.stream().anyMatch(shape -> shape.hasPoint(vertex));
	}

	@Override
	public Vertex getCenter() {
		List<Vertex> vertices = getVertices();
		int xMin = find(Integer::min, vertices, Vertex::x);
		int xMax = find(Integer::max, vertices, Vertex::x);

		int yMin = find(Integer::min, vertices, Vertex::y);
		int yMax = find(Integer::max, vertices, Vertex::y);

		return Vertex.at((xMin + xMax) / 2, (yMin + yMax) / 2);
	}

	private int find(IntBinaryOperator operator, List<Vertex> vertices, Function<Vertex, Double> getter) {
		return vertices.stream().mapToInt(vertex -> getter.apply(vertex).intValue()).reduce(operator).orElse(0);
	}

	@Override
	public Shape setCenter(Vertex vertex) {
		Vertex distance = getCenter().subtract(vertex);
		return new ShapeComposite(shapes.stream()
				.map(shape -> {
					Vertex relative = shape.getCenter().subtract(distance);
					return shape.setCenter(relative);
				})
				.collect(Collectors.toList()));
	}
	
	@Override
	public void accept(ShapeVisitor visitor) {
		visitor.visit(this);
	}
}
