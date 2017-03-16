package view;

import controller.DrawPanelController;
import controller.MainController;
import model.MainModel;
import model.ShapeModel;

import javax.swing.*;

public class Program {
    public static void main(String[] args) {
        MainModel model = new MainModel();
        MainController controller = new MainController(model);

        SwingUtilities.invokeLater(() -> new MainFrame(model, controller));
    }
}
