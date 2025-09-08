package budget.ui;

import budget.application.BudgetCategory;

import javax.sql.rowset.CachedRowSet;

public interface BudgetDbCalls {
    CachedRowSet getBudgetCategoryCategory();
    String updateBudgetCategory(DataRow<BudgetCategory> updateRow);

}
