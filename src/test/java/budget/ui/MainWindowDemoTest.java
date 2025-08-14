package budget.ui;

import budget.data.TestDataProvider;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class MainWindowDemoTest {
    private MainWindow mainWindow;
    private JButton refreshButton;
    private JTable userTable;

    @BeforeEach
    void setUp() throws Exception {
        SwingUtilities.invokeAndWait(() -> {
            mainWindow = new MainWindow();
             // Show window for demo
            mainWindow.setVisible(true);
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

    /*
     * This exception (`java.io.IOException: An existing connection was forcibly closed by the remote host`) is **not** typical for pure Java Swing/JUnit tests that just open a window and wait for user input. It usually appears when there is some network communication involved (e.g., database, REST APIs, sockets). Since your test is focused on showing a Swing UI and using a local `TestDataProvider`, this error is unexpected.
     *
     * ### Possible Causes
     *
     * 1. **IDE/Test Runner Interference:**
     *    If you close the test window while the test is still sleeping or running, some IDEs (especially IntelliJ IDEA, Eclipse, or Gradle test runners) could forcibly interrupt or terminate the JVM/test process, resulting in that error.
     *
     * 2. **JUnit/Test Runner Timeouts:**
     *    If your test takes "too long" (e.g., you leave the UI open), the test runner may kill the test process, causing this exception.
     *
     * 3. **Unrelated Background Processes:**
     *    If you have other code (or libraries) in your project that opens network connections even in test scope, it could trigger this error, but based on your provided files, this is unlikely.
     *
     * ### How to Clean Up the Experience
     *
     * - **Remove Sleep in Demo Tests:**
     *   Instead of using `Thread.sleep()` in your demo/manual test, use a loop that only returns when the window is closed, as described before:
     *
     *   ```java
     *   @Test
     *   void demoRefreshButton() throws Exception {
     *       // Setup is already done in @BeforeEach
     *       while (mainWindow.isDisplayable()) {
     *           Thread.sleep(100);
     *       }
     *   }
     *   ```
     *   This way, the test will only finish cleanly when the user closes the window.
     *
     * - **Mark Manual/Demo Tests to Exclude from CI:**
     *   Use `@Tag("manual")` (JUnit 5) or a similar mechanism so automated runs don’t trigger these tests.
     *
     * - **Avoid Closing the Window Abruptly:**
     *   Always close the window using the "X" (close) button, not by stopping the test run in the IDE.
     *
     * - **Check Test Runner Settings:**
     *   Some IDEs or build tools have a "test timeout" setting. Increase this if you want to keep the window open longer.
     *
     * ### Example Clean Demo Test
     *
     * ```java
     * @Test
     * void demoRefreshButton() throws Exception {
     *     // Window is already visible from @BeforeEach
     *     // Wait for user to close window manually
     *     while (mainWindow.isDisplayable()) {
     *         Thread.sleep(100);
     *     }
     * }
     * ```
     *
     * ### Summary
     *
     * - The error is likely from the test runner forcibly closing the JVM/test when you close the window or when the test times out.
     * - Use a loop that checks `mainWindow.isDisplayable()` (not a fixed `sleep`), so the test ends gracefully only when you close the UI yourself.
     * - Tag or separate these manual/demo tests from automated ones.
     *
     * Let me know if you want a cleaned-up version of your test code!
     *
     */
    @Test
    void demoRefreshButton() throws Exception {

        while (mainWindow.isDisplayable()){
            Thread.sleep(600);
        }

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