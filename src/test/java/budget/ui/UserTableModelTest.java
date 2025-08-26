package budget.ui;

import budget.data.DataProvider;
import budget.data.TestDataProvider;
import budget.data.UserRecord;

import org.junit.jupiter.api.*;


import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserTableModelTest {

    private UserTableModel model;
    private DataProvider dataProvider;
    private List<Object[]> testData;

    @BeforeEach
    public void setUp() {
        model = new UserTableModel();
        dataProvider = new TestDataProvider();
        testData = createTestData();
    }
    private List<Object[]> createTestData() {
        List<Object[]> list = new ArrayList<Object[]>() {{
            add(new Object[]{1, "john_doe", 25});
            add(new Object[]{2, "jane_smith", 30});
            add(new Object[]{3, "bob_wilson", 45});
            add(new Object[]{4, "alice_brown", 28});
            add(new Object[]{5, "charlie_davis", 35});
            add(new Object[]{6, "emma_white", 31});
        }};
        return list;
    }

    @Test
    public void testGetRowCount() {
        model.getData(dataProvider);
        assertEquals(testData.size(), model.getRowCount());
    }

    @Test
    public void testGetColumnCount() {
        assertEquals(3, model.getColumnCount());
    }

    @Test
    public void testGetColumnName() {
        assertEquals("ID", model.getColumnName(0));
        assertEquals("Username", model.getColumnName(1));
        assertEquals("Age", model.getColumnName(2));
    }

    @Test
    public void testGetColumnType() {
        assertEquals(int.class,model.getColumnClass(0) );
        assertEquals(String.class,model.getColumnClass(1) );
        assertEquals(Double.class,model.getColumnClass(2) );
    }

    /**
     * Tests getData
    */
    @Test
    public void testGetValueAt() {
        model.getData(dataProvider);

        for (int i = 0; i < testData.size(); i++) {
            Object[] expectedRow = testData.get(i);
            assertEquals(expectedRow[0], model.getValueAt(i, 0), "ID mismatch at row " + i);
            assertEquals(expectedRow[1], model.getValueAt(i, 1), "Username mismatch at row " + i);
            assertEquals(expectedRow[2], model.getValueAt(i, 2), "Age mismatch at row " + i);
        }
    }


    @Test
    public void testClearData() {
        model.getData(dataProvider);
        model.clearData();
        assertEquals(0, model.getRowCount());
    }




    }
