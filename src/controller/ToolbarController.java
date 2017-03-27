package controller;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.function.Consumer;

import javax.imageio.ImageIO;

import model.DrawableShape;
import model.MainModel;
import model.ShapeFileWriter;
import shape.ShapeComposite;

public class ToolbarController {

	private final MainModel model;
	private final DrawPanelController drawPanelController;
	private final CommandHistory history;

	ToolbarController(MainModel model, MainController mainController) {
		this.drawPanelController = mainController.getDrawPanelController();
		this.model = model;
		this.history = mainController.getHistory();
	}

	public void setCreationMode(String shapeName) {
		drawPanelController.setState(new ShapeCreationState(model, history, shapeName));
	}

	public void setDefaultMode() {
		drawPanelController.setState(drawPanelController.getDefaultState());
	}

	public void selectAll() {
		for (DrawableShape shape : model.getShapeModel().getShapes()) {
			model.getShapeModel().updateShape(shape, s -> s.setSelected(true));
		}
	}

	public void saveSelected(String shapeName) {
		List<DrawableShape> selected = drawPanelController.getPositionHandler().getSelected();
		if (!selected.isEmpty() && shapeName != null && shapeName != "") {
			ShapeComposite shape = new ShapeComposite();
			for (DrawableShape drawableShape : selected) {
				shape.addShape(drawableShape);
			}
			ShapeFileWriter.writeShapeToFile(shapeName, new DrawableShape(shape));
		}
		model.getShapeMakerModel().reinitialize();
	}

	public void saveGraphicsAsImage(int width, int height, Consumer<Graphics2D> painter, String fileName) {
		if (fileName != null && !"".equals(fileName)) {
			BufferedImage img = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
			painter.accept(img.createGraphics());
			try {
				File saveDirectory = new File("img/");
				if(!saveDirectory.exists()) {
					saveDirectory.mkdirs();
				}
				ImageIO.write(img, "png", new File(saveDirectory, fileName + ".png"));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
