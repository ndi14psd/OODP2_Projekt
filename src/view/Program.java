package view;

import controller.DrawPanelController;
import controller.MainController;
import model.ShapeModel;

import javax.swing.*;

public class Program {
    public static void main(String[] args) {
        ShapeModel model = new ShapeModel();
        MainController controller = new MainController(model);

        SwingUtilities.invokeLater(() -> new MainFrame(model, controller));
    }
}
