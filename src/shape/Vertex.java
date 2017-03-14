package shape;

import java.util.HashMap;
import java.util.Map;

public final class Vertex  {

    private final double x;
    private final double y;

    private final static Map<Double, Map<Double, Vertex>> CACHE = new HashMap<>();

    private Vertex(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public static Vertex at(double x, double y) {
        CACHE.putIfAbsent(x, new HashMap<>());
        CACHE.get(x).putIfAbsent(y, new Vertex(x, y));
        return CACHE.get(x).get(y);
    }

    public double x() {
        return x;
    }

    public double y() {
        return y;
    }

    public static Vertex add(Vertex p1, Vertex p2) {
        return Vertex.at(p1.x + p2.x, p1.y + p2.y);
    }

    public static Vertex subtract(Vertex v1, Vertex v2) {
        return Vertex.at(v1.x - v2.x, v1.y - v2.y);
    }

    public Vertex add(Vertex v) {
        return Vertex.add(this, v);
    }

    public Vertex subtract(Vertex v) {
        return Vertex.subtract(this, v);
    }

    public Vertex negative() {
        return Vertex.at(-x, -y);
    }

    @Override
    public String toString() {
        return String.format("[x: %f, y: %f]", x, y);
    }
}
