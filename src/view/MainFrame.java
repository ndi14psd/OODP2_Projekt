package view;

import javax.swing.*;

import controller.DrawPanelController;
import controller.MainController;
import model.MainModel;
import model.ShapeModel;

import java.awt.*;

public class MainFrame extends JFrame {

    MainFrame(MainModel model, MainController controller) {
        super("Application");
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());

        ToolbarPanel toolbarPanel = new ToolbarPanel(model.getShapeMakerModel(), controller.getToolbarController());
        DrawPanel drawPanel = new DrawPanel(model.getShapeModel(), controller.getDrawPanelController());
        drawPanel.setComponentPopupMenu(new AttributePopUpMenu(model.getAttributeModel(), controller.getAttributeController()));

        this.add(toolbarPanel, BorderLayout.WEST);
        this.add(drawPanel, BorderLayout.CENTER);

        pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

}
