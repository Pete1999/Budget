package budget.application;

import budget.ui.MainWindow;
import javax.swing.SwingUtilities;

public class BudgetApplication {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MainWindow mainWindow = new MainWindow();
            mainWindow.setVisible(true);
        });
    }
}