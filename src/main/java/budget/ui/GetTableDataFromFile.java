package budget.ui;

import java.util.EnumSet;
import java.util.Map;

public class GetTableDataFromFile implements IGetTableData {
    String file = "test_data_budget_category.txt";
    private EnumSet rowModel = EnumSet.allOf(BudgetCategory.class);

    @Override
    public Map<Integer, Map<DDLEnum, Object>> getData() {
        return DataTableModelFactoryEnum.loadDataFile(file,rowModel);
    }
}
