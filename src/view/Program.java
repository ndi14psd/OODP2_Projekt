package view;

import javax.swing.SwingUtilities;

import controller.MainController;
import model.MainModel;

public class Program {
    public static void main(String[] args) {
        MainModel model = new MainModel();
        MainController controller = new MainController(model);

        SwingUtilities.invokeLater(() -> new MainFrame(model, controller));
    }
}
