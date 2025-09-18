package budget.ui;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.swing.table.TableModel;

import static org.junit.jupiter.api.Assertions.*;

class BudgetCategoryTableModelTest {
    BudgetCategoryTableModel subject;

    @BeforeEach
    void setUp() {
        subject = BudgetCategoryTableModel.getInstance();
    }

    @Test
    void testGetTableModel() {
        TableModel tm = BudgetCategoryTableModel.getTableModel();
        assertInstanceOf(TableModel.class, tm);

    }

    @Test
    void getColumnClassTest() {

        assertEquals(Integer.class, subject.getColumnClass(3));
        assertEquals(String.class, subject.getColumnClass(0));
    }
}