package budget.ui;





import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.*;

public class MainWindow extends JFrame {
    private final JTable userTable;
    private final TableModel tableModel;
    private final JButton refreshButton;
    private final JButton insertButton;
    private final JLabel statusBar;

    public MainWindow() {
        setTitle("Budget Application");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);
        BudgetCategoryData budgetCategoryData = new BudgetCategoryData();
        // Create components

        tableModel = budgetCategoryData.getTableModel();
        userTable = new JTable(tableModel);
        refreshButton = new JButton("Refresh");
        insertButton = new JButton("Insert New User");
        statusBar = new JLabel(" ");

        // Layout
        JPanel toolBar = new JPanel(new FlowLayout(FlowLayout.LEFT));
        toolBar.add(refreshButton);
        toolBar.add(insertButton);

        // Main layout
        setLayout(new BorderLayout());
        add(toolBar, BorderLayout.NORTH);
        add(new JScrollPane(userTable), BorderLayout.CENTER);
        add(statusBar, BorderLayout.SOUTH);

        // Add listeners
        refreshButton.addActionListener(e -> refreshData());
        insertButton.addActionListener(e -> showInsertDialog());
    }

    private void refreshData() {
        // This would be connected to your data source later
        statusBar.setText("Refreshing data...");
        SwingWorker<Void, Void> worker = new SwingWorker<>() {
            @Override
            protected Void doInBackground() {
                // Simulating data fetch
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
                return null;
            }

            @Override
            protected void done() {
                statusBar.setText("Data refreshed successfully");
            }
        };
        worker.execute();
    }

    private void showInsertDialog() {
        UserInputDialog dialog = new UserInputDialog(this);
        dialog.setVisible(true);
    }
}