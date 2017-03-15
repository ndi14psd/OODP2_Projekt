package shape;

import java.io.Serializable;
import java.util.List;

public interface Shape extends Serializable {
	
	List<Vertex> getVertices();
	
    boolean hasPoint(Vertex vertex);

    Vertex getCenter();

    Shape setCenter(Vertex vertex);

    void accept(ShapeVisitor visitor);
}
