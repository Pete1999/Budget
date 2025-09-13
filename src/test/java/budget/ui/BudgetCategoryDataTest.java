package budget.ui;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

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
}