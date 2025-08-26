package budget.ui;

import java.util.ArrayList;
import java.util.List;

public class MockDataTableModelEnum implements DDLEnum {
    private List<String> data;

    public MockDataTableModelEnum() {
        this.data = new ArrayList<>();
    }

    public void setTableData(Class<?> clazz, List<String> data) {
        this.data = data;
    }

    public int getNumRows() {
        return data.size();
    }

    public int getNumColumns() {
        return 1; // Assuming one column for simplicity
    }

    public Object getData(int rowIndex, int columnIndex) {
        return data.get(rowIndex);
    }

    public void setData(Object aValue, int rowIndex, int columnIndex) {
        data.set(rowIndex, (String) aValue);
    }

    public DDLEnum findColEnum(int columnIndex) {
        return this;
    }

    @Override
    public String getColName() {
        return "Category";
    }

    @Override
    public Class<?> getClassType() {
        return String.class;
    }
}
