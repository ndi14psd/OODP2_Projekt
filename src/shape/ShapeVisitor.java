package shape;

public interface ShapeVisitor {

    void visit(ShapeComposite composite);

    void visit(Circle circle);
    
    void visit(Rectangle rectangle);

	void visit(Square square);

}
