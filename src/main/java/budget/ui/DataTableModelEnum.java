package budget.ui;

import javax.sql.RowSet;
import java.util.EnumSet;
import java.util.Map;

public class DataTableModelEnum<T extends Enum<T> & DDLEnum> implements IDataTableModelEnum<T> {

    private EnumSet<T> rowModel;
    private Map<Integer, Map<DDLEnum, Object>> tableData;

    DataTableModelEnum(Class<T> ddl, RowSet rs) {

        this.rowModel = EnumSet.allOf(ddl);
        this.tableData = DataTableModelFactoryEnum.loadSqlResultSet(rs, rowModel);
    }

    public DataTableModelEnum() {

    }

    @Override
    public void setTableData(Class<T> clazz, RowSet rs) {
        rowModel = EnumSet.allOf(clazz);
        this.tableData = DataTableModelFactoryEnum.loadSqlResultSet(rs,rowModel);
    }

    @Override
    public int getNumColumns() {
        return rowModel.size();
    }

    @Override
    public int getNumRows() {
        return tableData.size();
    }

    @Override
    public int getColIdx(String colName) {

        //find the enum for col header
        int i = 0;
        for (T e :
                rowModel) {
            if (e.getColName().equals(colName)) {
                i = e.ordinal();
                break;

            }
        }
        return i;
    }

    @Override
    public int getEnumColIndex(T ddl) {
        return ddl.ordinal();
    }



    @Override
    public DDLEnum findColEnum(int colIndex) {
        DDLEnum ddl = null;
        for (Enum<?> e :
                rowModel) {
            if (e.ordinal() == colIndex) {
                ddl = (DDLEnum) e;
                break;
            }

        }
        return ddl;
    }

    @Override
    public void setData(Object aValue, int rowIndex, int colIndex) {
        tableData.get(rowIndex).put(findColEnum(colIndex), aValue);
    }

    @Override
    public Object getData(int rowIndex, int colIndex) {
        return tableData.get(rowIndex).get(findColEnum(colIndex));
    }

    @Override
    public <U> void putData(int rowIndex, T ddl, U data) {
        Class<?> ddlClass = ddl == null ? null : ddl.getClassType();
        if (ddlClass == null || (!(ddlClass.isInstance(data)))) throw new IllegalArgumentException();

        tableData.get(rowIndex).put(ddl, ddlClass.cast(data));

    }

    @Override
    public <U> U getData(int rowData, T ddl, Class<U> uClass) {
        if (ddl == null || uClass == null
                || !(ddl.getClassType().isAssignableFrom(uClass))) {
            throw new IllegalArgumentException();
        }

        return uClass.cast(tableData.get(rowData).get(ddl));

    }

}
