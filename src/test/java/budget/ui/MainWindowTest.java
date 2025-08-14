package budget.ui;

import budget.data.TestDataProvider;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class MainWindowTest {
    private MainWindow mainWindow;
    private JButton refreshButton;
    private JTable userTable;
    private List<Object[]> testData;

    @BeforeEach
    void setUp() throws Exception {
        SwingUtilities.invokeAndWait(() -> {
            mainWindow = new MainWindow();
            mainWindow.setVisible(true); // Show window for demo
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

        testData = createTestData();
    }

    private List<Object[]> createTestData() {
        List<Object[]> list = new ArrayList<Object[]>() {{
            add(new Object[]{1, "john_doe", 25});
            add(new Object[]{2, "jane_smith", 30});
            add(new Object[]{3, "bob_wilson", 45});
            add(new Object[]{4, "alice_brown", 28});
            add(new Object[]{5, "charlie_davis", 35});
            add(new Object[]{6, "emma_white", 31});
        }};
        return list;
    }

    @Test
    void testRefreshButton() throws Exception {
        // For demonstration, let the user click the button.
        // Optionally, uncomment below to auto-click for test
        SwingUtilities.invokeAndWait(() -> refreshButton.doClick());

        // Wait up to 60 seconds for demo
        Thread.sleep(6000);

        // Now check the table contents after (potentially) manual refresh
        SwingUtilities.invokeAndWait(() -> {
            UserTableModel model = (UserTableModel) userTable.getModel();
            assertEquals(6, model.getRowCount(), "Table should have 6 rows");

            for (int i = 0; i < testData.size(); i++) {
                Object[] expectedRow = testData.get(i);
                assertEquals(expectedRow[0], model.getValueAt(i, 0), "ID mismatch at row " + i);
                assertEquals(expectedRow[1], model.getValueAt(i, 1), "Username mismatch at row " + i);
                assertEquals(expectedRow[2], model.getValueAt(i, 2), "Age mismatch at row " + i);
            }
        });
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