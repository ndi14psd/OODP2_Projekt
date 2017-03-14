package view;

import controller.DrawPanelController;
import model.ShapeModel;

import javax.swing.*;

public class Program {
    public static void main(String[] args) {
        ShapeModel model = new ShapeModel();
        DrawPanelController controller = new DrawPanelController(model);

        SwingUtilities.invokeLater(() -> new MainFrame(model, controller));
    }
}
