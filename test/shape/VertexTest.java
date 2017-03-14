package shape;


import org.junit.Test;

import static junit.framework.TestCase.assertEquals;


public class VertexTest {

    @Test
    public void createPointObject() {
        Vertex vertex = Vertex.at(0d, 1d);
        assertEquals(0d, vertex.x());
        assertEquals(1d, vertex.y());
    }

    @Test
    public void addTwoPoints() {
        Vertex p1 = Vertex.at(4, 5);
        Vertex p2 = Vertex.at(5, 1);
        Vertex sum = Vertex.add(p1, p2);
        assertEquals(9d, sum.x());
        assertEquals(6d, sum.y());
    }



}