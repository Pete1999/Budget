package budget.ui;

import budget.ui.MainWindow;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MainWindowTest {
    private MainWindow mainWindow;
    private JButton refreshButton;
    private JTable userTable;
    private List<String[]> testData;

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
        testData = loadTestData("test_data.txt");
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
            assertEquals(6, model.getRowCount(), "Table should have 6 rows");
            
            // Verify each row matches test data
            for (int i = 0; i < testData.size(); i++) {
                String[] expectedRow = testData.get(i);
                assertEquals(Integer.parseInt(expectedRow[0]), model.getValueAt(i, 0), 
                    "ID mismatch at row " + i);
                assertEquals(expectedRow[1], model.getValueAt(i, 1), 
                    "Username mismatch at row " + i);
                assertEquals(Integer.parseInt(expectedRow[2]), model.getValueAt(i, 2), 
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

    private List<String[]> loadTestData(String filename) throws IOException {
        List<String[]> data = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                data.add(line.split(","));
            }
        }
        return data;
    }
}