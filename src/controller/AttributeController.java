package controller;

import java.awt.Color;
import java.util.function.Consumer;

import model.Attribute;
import model.AttributeModel;
import model.ShapeModel;

public class AttributeController {

	private final AttributeModel attributeModel;
	private final ShapeModel shapeModel;

	public AttributeController(ShapeModel shapeModel, AttributeModel attributeModel) {
		this.shapeModel = shapeModel;
		this.attributeModel = attributeModel;
	}
	
	public void setColor(Color color) {
		attributeModel.getShape().ifPresent(shape -> {
			int rgb = color.getRGB();
			Consumer<String> colorSetter = attributeModel.getColorAttribute().getUpdater();
			String stringRGB = Integer.toString(rgb);
			shapeModel.updateShape(shape, s -> colorSetter.accept(stringRGB));
		});
	}

	public void updateAttribute(Attribute attribute, String stringValue) {
		attributeModel.getShape().ifPresent(shape -> {
			shapeModel.updateShape(shape, s -> attribute.getUpdater().accept(stringValue));
		});
	}
	
}
