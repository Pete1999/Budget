package budget.ui;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import javax.sql.RowSet;
import javax.sql.rowset.CachedRowSet;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

public class BudgetCategoryModelTest {

    private final CachedRowSet testData = mock(CachedRowSet.class);
    private BudgetCategoryModel model;
    private IDataTableModelEnum<BudgetCategory> mockDtm = new DataTableModelEnum();


    @BeforeEach
    public void setUp() {
        DataTableModelFactoryEnum factoryEnum = mock(DataTableModelFactoryEnum.class);
        RowSet rs = mock(RowSet.class);
        EnumSet<BudgetCategory> rowModel = EnumSet.allOf(BudgetCategory.class);
        when(factoryEnum.loadSqlResultSet(rs,rowModel)).thenReturn(fakeloadSqlResultSet());

        model = new BudgetCategoryModel(mockDtm);

    }

    private Map<Integer, Map<DDLEnum, Object>> fakeloadSqlResultSet() {
        Map<Integer, Map<DDLEnum, Object>> dataTable = new HashMap<>();
        return dataTable;
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
@Disabled("")
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
