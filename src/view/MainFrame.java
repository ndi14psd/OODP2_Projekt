package view;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

import controller.MainController;
import model.MainModel;

public class MainFrame extends JFrame {

    MainFrame(MainModel model, MainController controller) {
        super("Application");
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());

        ToolbarPanel toolbarPanel = new ToolbarPanel(model.getShapeMakerModel(), controller.getToolbarController());
        DrawPanel drawPanel = new DrawPanel(model.getShapeModel(), controller.getDrawPanelController());
        drawPanel.setComponentPopupMenu(new ShapePopUpMenu(model.getAttributeModel(), controller.getShapeOptionController()));

        this.add(toolbarPanel, BorderLayout.WEST);
        this.add(drawPanel, BorderLayout.CENTER);

        pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

}
