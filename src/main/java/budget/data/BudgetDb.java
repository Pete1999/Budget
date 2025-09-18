package budget.data;

import budget.model.BudgetCategory;
import budget.data.DataRow;

import javax.sql.rowset.CachedRowSet;

public class BudgetDb implements BudgetDbCalls {
    private DataRow<BudgetCategory> dataRow;

    public void setDataRow(DataRow<BudgetCategory> dataRow) {
        this.dataRow = dataRow;
    }

    @Override
    public CachedRowSet getBudgetCategoryCategory() {
        return Db.getBudgetCategoryCategory();
    }

    @Override
    public String updateBudgetCategory(DataRow<BudgetCategory> updateRow) {
        return Db.updateBudgetCategory(dataRow);
    }
}
