package budget.ui;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.sql.rowset.CachedRowSet;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;

public class BudgetCategoryModelTest {

    private final CachedRowSet testData = mock(CachedRowSet.class);
    private BudgetCategoryModel model;
    private MockDataTableModelEnum mockDtm;


    @BeforeEach
    public void setUp() {
        mockDtm = new MockDataTableModelEnum();
        model = new BudgetCategoryModel(mockDtm);
//        model.dtmEnum = mockDtm; // Inject the mock implementation
    }

    @Test
    public void testListBudgetCategory() {
        mockDtm.setTableData(BudgetCategory.class, testData);

        List<String> result = model.listBudgetCategory();
        assertEquals(testData, result);
    }

    @Test
    public void testSaveChanges() {
        // Implement this test
    }

    @Test
    public void testAddRow() {
        // Implement this test
    }

    @Test
    public void testGetRowCount() {
        mockDtm.setTableData(BudgetCategory.class, testData);

        int result = model.getRowCount();
        assertEquals(testData.size(), result);
    }

    @Test
    public void testGetColumnCount() {
        int result = model.getColumnCount();
        assertEquals(1, result); // Assuming one column for simplicity
    }

    @Test
    public void testGetValueAt() {
        mockDtm.setTableData(BudgetCategory.class, testData);

        Object result = model.getValueAt(1, 0);
        assertEquals("Category2", result);
    }

    @Test
    public void testSetValueAt() {

        mockDtm.setTableData(BudgetCategory.class, testData);

        model.setValueAt("NewCategory", 1, 0);
        Object result = model.getValueAt(1, 0);
        assertEquals("NewCategory", result);
    }

    @Test
    public void testIsCellEditable() {
        boolean result = model.isCellEditable(0, 0);
        assertTrue(result);
    }

    @Test
    public void testTableChanged() {
        // Implement this test
    }
}
