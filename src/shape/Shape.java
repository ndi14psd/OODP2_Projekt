package shape;

import java.util.List;

public interface Shape {
	
	List<Vertex> getVertices();
	
    boolean hasPoint(Vertex vertex);

    Vertex getCenter();

    Shape moveCenter(Vertex vertex);

    void accept(ShapeVisitor visitor);
}
