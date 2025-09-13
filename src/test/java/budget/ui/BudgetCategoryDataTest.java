package budget.ui;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.swing.table.TableModel;
import java.util.ArrayList;

class BudgetCategoryDataTest {
    BudgetCategoryData subject;
    @BeforeEach
    void setUp() {
        subject = new BudgetCategoryData();
    }

    @Test
    void testReadFileData() {
      ArrayList<ArrayList<Object>> data =  subject.getDataFromFile("test_data_budget_category.txt");
        System.out.println(data);
    }



    @Test
    void testGetTableModel() {
        TableModel tm = subject.getTableModel();
        System.out.println(tm);

    }
}