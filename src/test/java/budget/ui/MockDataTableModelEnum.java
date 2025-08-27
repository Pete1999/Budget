package budget.ui;

import javax.sql.RowSet;

public class MockDataTableModelEnum implements IDataTableModelEnum<BudgetCategory> {


    @Override
    public void setTableData(Class<BudgetCategory> clazz, RowSet rs) {

    }

    @Override
    public int getNumColumns() {
        return 0;
    }

    @Override
    public int getNumRows() {
        return 0;
    }

    @Override
    public int getColIdx(String colName) {
        return 0;
    }

    @Override
    public int getEnumColIndex(BudgetCategory ddl) {
        return 0;
    }

    @Override
    public DDLEnum findColEnum(int colIndex) {
        return null;
    }

    @Override
    public void setData(Object aValue, int rowIndex, int colIndex) {

    }

    @Override
    public Object getData(int rowIndex, int colIndex) {
        return null;
    }

    @Override
    public <U> void putData(int rowIndex, BudgetCategory ddl, U data) {

    }

    @Override
    public <U> U getData(int rowData, BudgetCategory ddl, Class<U> uClass) {
        return null;
    }
}
