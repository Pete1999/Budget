package budget.ui;

import budget.data.TestDataProvider;
import budget.data.UserRecord;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class MainWindowTest {
    private MainWindow mainWindow;
    private JButton refreshButton;
    private JTable userTable;
    private List<Object[]> testData;


    @BeforeEach
    void setUp() throws Exception {
        // Create and show window on EDT
        SwingUtilities.invokeAndWait(() -> {
            mainWindow = new MainWindow();
            // Get components using Component hierarchy
            refreshButton = findButtonByText(mainWindow, "Refresh");
            userTable = findJTable(mainWindow);
        });
        
        // Load test data
       testData = createTestData();
    }

    private List<Object[]> createTestData() {
        List<Object[]> list = new ArrayList<Object[]>() {{
            add(new Object[]{1,"john_doe",25});
            add(new Object[]{2,"jane_smith",30});
            add(new Object[]{3,"bob_wilson",45});
            add(new Object[]{4,"alice_brown",28});
            add(new Object[]{5,"charlie_davis",35});
            add(new Object[]{6,"emma_white",31});
        }};
        return list;
    }

    @Test
    void testRefreshButton() throws Exception {
        // Click refresh button on EDT
        SwingUtilities.invokeAndWait(() -> refreshButton.doClick());
        
        // Wait for potential SwingWorker to complete (max 2 seconds)
        Thread.sleep(2000);
        
        // Verify table contents on EDT
        SwingUtilities.invokeAndWait(() -> {
            UserTableModel model = (UserTableModel) userTable.getModel();
            model.getData(new TestDataProvider());

            assertEquals(6, model.getRowCount(), "Table should have 6 rows");

            // Verify each row matches test data
            for (int i = 0; i < testData.size(); i++) {
                Object[] expectedRow = testData.get(i);
                assertEquals(expectedRow[0], model.getValueAt(i, 0),
                    "ID mismatch at row " + i);
                assertEquals(expectedRow[1], model.getValueAt(i, 1), 
                    "Username mismatch at row " + i);
                assertEquals(expectedRow[2], model.getValueAt(i, 2),
                    "Age mismatch at row " + i);
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