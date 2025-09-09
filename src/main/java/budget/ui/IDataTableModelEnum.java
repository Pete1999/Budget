package budget.ui;

public interface IDataTableModelEnum<T extends Enum<T> & DDLEnum> {
    void setTableData(Class<T> clazz, IGetTableData gtd);

    void setTableData(Class<T> clazz, String dataFile);

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
