package budget.ui;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import javax.sql.RowSet;
import javax.sql.rowset.CachedRowSet;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

public class BudgetCategoryModelTest {


    private BudgetCategoryModel budgetCategoryModel;



    @BeforeEach
    public void setUp() {
        DataTableModelEnum<BudgetCategory> testDataTableModelEnum = mock(DataTableModelEnum.class);
//        try(
//        MockedStatic<DataTableModelFactoryEnum> dataSource = mockStatic(DataTableModelFactoryEnum.class);
//        MockedStatic<Db> db = mockStatic(Db.class)){
//
//            dataSource.when(
//                    () -> DataTableModelFactoryEnum.loadSqlResultSet(any(),any())).thenReturn(fakeloadSqlResultSet());
//            db.when(() -> Db.getConnection()).thenAnswer(invocationOnMock -> null);
//            db.when(() -> Db.loadProperties(any())).thenReturn(null);
//            db.when(() -> Db.getBudgetCategoryCategory()).thenReturn(null);
//        };

        //when(testDataTableModelEnum.setTableData(BudgetCategory.class,any())).thenReturn(fakeloadSqlResultSet());
////        doNothing().when(testDataTableModelEnum).setTableData(any(BudgetCategory.class).getDeclaringClass(),any());



//        budgetCategoryModel = new BudgetCategoryModel(testDataTableModelEnum);




    }

    @Test
    public void testListBudgetCategory() {
        System.out.println(Mockito.class.getPackage().getImplementationVersion());

      //  List<String> result = budgetCategoryModel.listBudgetCategory();
//        assertEquals(testData, result);
    }

    @Test
    public void testSaveChanges() {
        // Implement this test
    }
    @Test
    public void testAddRow() {
        // Implement this test
    }
    @Disabled("This is just a wrapper")
    @Test
    public void testGetRowCount() {

    }

    @Test
    public void testGetColumnCount() {
        int result = budgetCategoryModel.getColumnCount();
        assertEquals(1, result); // Assuming one column for simplicity
    }

    @Test
    public void testGetValueAt() {

        Object result = budgetCategoryModel.getValueAt(1, 0);
        assertEquals("Category2", result);
    }

    @Test
    public void testSetValueAt() {


        budgetCategoryModel.setValueAt("NewCategory", 1, 0);
        Object result = budgetCategoryModel.getValueAt(1, 0);
        assertEquals("NewCategory", result);
    }

    @Test
    public void testIsCellEditable() {
        boolean result = budgetCategoryModel.isCellEditable(0, 0);
        assertTrue(result);
    }

    @Test
    public void testTableChanged() {
        // Implement this test
    }
}
