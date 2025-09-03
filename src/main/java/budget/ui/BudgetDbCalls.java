package budget.ui;

import javax.sql.rowset.CachedRowSet;

public interface BudgetDbCalls {
    CachedRowSet getBudgetCategoryCategory();
    String updateBudgetCategory(DataRow<BudgetCategory> updateRow);

}
