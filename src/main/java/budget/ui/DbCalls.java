package budget.ui;

import javax.sql.rowset.CachedRowSet;

public interface DbCalls {
    CachedRowSet getBudgetCategoryCategory();
    String updateBudgetCategory(DataRow<BudgetCategory> updateRow);

}
