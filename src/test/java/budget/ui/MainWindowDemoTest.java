package budget.ui;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.swing.*;

public class MainWindowDemoTest {
    private MainWindow mainWindow;

    @BeforeEach
    void setUp() throws Exception {
        SwingUtilities.invokeAndWait(() -> {
            mainWindow = new MainWindow();
            // Show window for demo
            mainWindow.setVisible(true);

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

        while (mainWindow.isDisplayable()) {
            Thread.sleep(600);
        }

    }


}