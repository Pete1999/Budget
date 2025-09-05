package budget.ui;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class GetTableDataFromFileTest {
    private GetTableDataFromFile gettest;
    @BeforeEach
    void setUp() {
        gettest = new GetTableDataFromFile();
    }

    @Test
    public void getData(){
        int expected = 578;
        Map<Integer, Map<DDLEnum, Object>> res =   gettest.getData();
        assertEquals(3,res.size());
        assertEquals("UTILS",res.get(1).get(BudgetCategory.CATEGORY));
        assertEquals(expected,res.get(1).get(BudgetCategory.TARGET));

    }

}