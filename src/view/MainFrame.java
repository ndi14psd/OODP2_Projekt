package view;

import javax.swing.*;

import controller.DrawPanelController;
import model.ShapeModel;

import java.awt.*;

public class MainFrame extends JFrame {

    MainFrame(ShapeModel model, DrawPanelController controller) {
        super("Application");
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());

        ToolbarPanel toolbarPanel = new ToolbarPanel();
        DrawPanel drawPanel = new DrawPanel(model, controller);

        this.add(toolbarPanel, BorderLayout.WEST);
        this.add(drawPanel, BorderLayout.CENTER);

        pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

}
