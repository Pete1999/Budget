package budget.ui;

import budget.ui.UserTableModel;
import budget.data.DataProvider;
import budget.data.UserRecord;

import org.junit.jupiter.api.*;


import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserTableModelTest {

    private UserTableModel model;
    private DataProvider dataProvider;

    @BeforeEach
    public void setUp() {
        model = new UserTableModel();
        dataProvider = new TestDataProvider();
    }

    @Test
    public void testGetRowCount() {
        model.getData(dataProvider);
        assertEquals(2, model.getRowCount());
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
    public void testGetValueAt() {
        model.getData(dataProvider);
        assertEquals(1, model.getValueAt(0, 0));
        assertEquals("user1", model.getValueAt(0, 1));
        assertEquals(25, model.getValueAt(0, 2));
        assertEquals(2, model.getValueAt(1, 0));
        assertEquals("user2", model.getValueAt(1, 1));
        assertEquals(30, model.getValueAt(1, 2));
    }

    @Test
    public void testGetData() {
        model.getData(dataProvider);
        assertEquals(2, model.getRowCount());
        assertEquals("user1", model.getValueAt(0, 1));
        assertEquals("user2", model.getValueAt(1, 1));
    }

    @Test
    public void testClearData() {
        model.getData(dataProvider);
        model.clearData();
        assertEquals(0, model.getRowCount());
    }



        private class TestDataProvider implements DataProvider {
            @Override
            public List<UserRecord> getData() {
                List<UserRecord> data = new ArrayList<>();
                data.add(new UserRecord(1, "user1", 25));
                data.add(new UserRecord(2, "user2", 30));
                return data;
            }
        }
    }
