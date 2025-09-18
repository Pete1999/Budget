package budget.data;

import budget.model.BudgetCategory;
import budget.data.DataRow;

import javax.sql.rowset.CachedRowSet;

public interface BudgetDbCalls {
    CachedRowSet getBudgetCategoryCategory();
    String updateBudgetCategory(DataRow<BudgetCategory> updateRow);

}
