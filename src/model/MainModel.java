package model;

public class MainModel {

	private final ShapeModel shapeModel;
	private final ShapeCreatorModel shapeMakerModel;
	private final ShapePropertyModel attributeModel;
	
	public MainModel() {
		shapeMakerModel = new ShapeCreatorModel();
		shapeModel = new ShapeModel();
		attributeModel = new ShapePropertyModel();
	}
	
	public ShapeModel getShapeModel() {
		return shapeModel;
	}
	
	public ShapeCreatorModel getShapeMakerModel() {
		return shapeMakerModel;
	}
	
	public ShapePropertyModel getAttributeModel() {
		return attributeModel;
	}
}
