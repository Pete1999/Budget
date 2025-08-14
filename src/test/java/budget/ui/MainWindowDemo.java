package budget.ui;

import budget.data.TestDataProvider;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

import static java.lang.Thread.sleep;

public class MainWindowDemo {
    private MainWindow mainWindow;
    private JButton refreshButton;
    private JTable userTable;

    @BeforeEach
    void setUp() throws Exception {
        SwingUtilities.invokeAndWait(() -> {
            mainWindow = new MainWindow();
             // Show window for demo
            refreshButton = findButtonByText(mainWindow, "Refresh");
            userTable = findJTable(mainWindow);

            // Replace the refresh action to use TestDataProvider
            for (ActionListener al : refreshButton.getActionListeners()) {
                refreshButton.removeActionListener(al);
            }
            refreshButton.addActionListener(e -> {
                UserTableModel model = (UserTableModel) userTable.getModel();
                model.getData(new TestDataProvider());
                model.fireTableDataChanged();
            });
        });

            }

    @Test
    void demoRefreshButton() throws Exception {

        mainWindow.setVisible(true);
        Thread.sleep(6000);
      }

    private JButton findButtonByText(Container container, String text) {
        for (Component comp : container.getComponents()) {
            if (comp instanceof JButton && ((JButton) comp).getText().equals(text)) {
                return (JButton) comp;
            } else if (comp instanceof Container) {
                JButton button = findButtonByText((Container) comp, text);
                if (button != null) {
                    return button;
                }
            }
        }
        return null;
    }

    private JTable findJTable(Container container) {
        for (Component comp : container.getComponents()) {
            if (comp instanceof JTable) {
                return (JTable) comp;
            } else if (comp instanceof JScrollPane) {
                Component view = ((JScrollPane) comp).getViewport().getView();
                if (view instanceof JTable) {
                    return (JTable) view;
                }
            } else if (comp instanceof Container) {
                JTable table = findJTable((Container) comp);
                if (table != null) {
                    return table;
                }
            }
        }
        return null;
    }
}