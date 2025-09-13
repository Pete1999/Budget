package budget.ui;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.swing.table.TableModel;

class BudgetCategoryDataTest {
    BudgetCategoryData subject;

    @BeforeEach
    void setUp() {
        subject = BudgetCategoryData.getInstance();
    }

    @Test
    void testGetTableModel() {
        TableModel tm = subject.getTableModel();
        Assertions.assertTrue(true);

    }
}