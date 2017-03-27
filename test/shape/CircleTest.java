package shape;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class CircleTest {

    @Test
    public void getDiameter() {
        Circle circle = Circle.get(Vertex.at(5, 5), 5);
        assertEquals(10d, circle.getDiameter());
    }

    @Test
    public void getArea() {
        Circle circle = Circle.get(Vertex.at(0, 0), 2);
        assertEquals(4 * Math.PI, circle.getArea());
    }

    @Test
    public void checkIfPointIsOnCircle() {
        Shape circle = Circle.get(Vertex.at(0, 0), 10);
        assertTrue(circle.hasPoint(Vertex.at(1, 1)));
        assertTrue(circle.hasPoint(Vertex.at(5, 5)));
        assertFalse(circle.hasPoint(Vertex.at(10, 10)));
    }

}