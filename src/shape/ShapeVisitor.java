package shape;

public interface ShapeVisitor {

    void visit(ShapeComposite composite);

    void visit(Circle circle);

}
