package budget.ui;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.swing.table.TableModel;

import static org.junit.jupiter.api.Assertions.*;

class BudgetCategoryDataTest {
    BudgetCategoryData subject;

    @BeforeEach
    void setUp() {
        subject = BudgetCategoryData.getInstance();
    }

    @Test
    void testGetTableModel() {
        TableModel tm = BudgetCategoryData.getTableModel();
        assertInstanceOf(TableModel.class, tm);

    }

    @Test
    void getColumnClassTest() {

        assertEquals(Integer.class, subject.getColumnClass(3));
        assertEquals(String.class, subject.getColumnClass(0));
    }
}