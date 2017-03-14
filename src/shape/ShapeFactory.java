package shape;


public final class ShapeFactory {

    private ShapeFactory() {
    }

    public static Shape getCircle(Vertex center, double radius) {
        return Circle.get(center, radius);
    }
}

