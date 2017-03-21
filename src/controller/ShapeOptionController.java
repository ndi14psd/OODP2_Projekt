package controller;

import java.awt.Color;

import model.ShapeModel;
import model.ShapePropertyModel;
import shape.Shape;

public class ShapeOptionController {

	private final ShapePropertyModel optionModel;
	private final ShapeModel shapeModel;

	public ShapeOptionController(ShapeModel shapeModel, ShapePropertyModel optionModel) {
		this.shapeModel = shapeModel;
		this.optionModel = optionModel;
	}
	
	public void executeShapeOption(String option, String value) {
		optionModel.getShape().ifPresent(shape -> {
			double val = Double.valueOf(value);
			Shape updatedProperty = optionModel.getUpdatedProperty(option, val);
			shapeModel.updateShape(shape, s -> s.setInnerShape(updatedProperty));
		});
	}

	public void setShapeColor(Color color) {
		optionModel.getShape().ifPresent(shape -> {
			shapeModel.updateShape(shape, s -> s.setColor(color));
		});
	}

	public void setShapeStrokeWidth(String strokeWidthValue) {
		optionModel.getShape().ifPresent(shape -> {
			double value = Double.valueOf(strokeWidthValue);
			shapeModel.updateShape(shape, s -> s.setStrokeWidth(value));
		});
	}
	
}
