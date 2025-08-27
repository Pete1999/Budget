package budget.ui;

import javax.sql.RowSet;

public interface IDataTableModelEnum<T extends Enum<T> & DDLEnum> {
    void setTableData(Class<T> clazz, RowSet rs);

    int getNumColumns();

    int getNumRows();

    int getColIdx(String colName);

    int getEnumColIndex(T ddl);

    DDLEnum findColEnum(int colIndex);

    void setData(Object aValue, int rowIndex, int colIndex);

    Object getData(int rowIndex, int colIndex);

    <U> void putData(int rowIndex, T ddl, U data);

    <U> U getData(int rowData, T ddl, Class<U> uClass);
}
