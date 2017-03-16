package model;

public class MainModel {

	private final ShapeModel shapeModel;
	private final ShapeCreatorModel shapeMakerModel;
	private final AttributeModel attributeModel;
	
	public MainModel() {
		shapeMakerModel = new ShapeCreatorModel();
		shapeModel = new ShapeModel();
		attributeModel = new AttributeModel(shapeModel);
	}
	
	public ShapeModel getShapeModel() {
		return shapeModel;
	}
	
	public ShapeCreatorModel getShapeMakerModel() {
		return shapeMakerModel;
	}
	
	public AttributeModel getAttributeModel() {
		return attributeModel;
	}
}
