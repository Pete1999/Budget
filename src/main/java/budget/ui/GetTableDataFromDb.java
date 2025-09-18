package budget.ui;

import budget.data.BudgetCategory;

import javax.sql.RowSet;
import java.util.EnumSet;
import java.util.Map;

public class GetTableDataFromDb implements IGetTableData {
    private EnumSet rowModel = EnumSet.allOf(BudgetCategory.class);
    private RowSet rs = Db.getBudgetCategoryCategory();
    @Override
    public Map<Integer, Map<DDLEnum, Object>> getData() {
        return DataTableModelFactoryEnum.loadSqlResultSet(rs,rowModel);
    }
}
