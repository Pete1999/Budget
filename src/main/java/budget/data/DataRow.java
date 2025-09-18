package budget.data;

import budget.model.DDLEnum;

import java.util.EnumMap;

class DataRow<T extends Enum<T> & DDLEnum> {
    // TODO: 2023-05-02 This class is part of DataTableModelEnum.

    private final EnumMap<T, Object> columnData;

    public DataRow(EnumMap<T, Object> columnData) {
        this.columnData = columnData;
    }

    <U> void putData(T ddl, U data) {
        Class<?> ddlClass = ddl == null ? null : ddl.getClassType();
        if (ddlClass == null || (!(ddlClass.isInstance(data)))) throw new IllegalArgumentException();

        columnData.put(ddl, ddlClass.cast(data));

    }

    <U> U getData(T ddl, Class<U> uClass) {
        if (ddl == null || uClass == null
                || !(ddl.getClassType().isAssignableFrom(uClass))) {
            throw new IllegalArgumentException();
        }

        return uClass.cast(columnData.get(ddl));

    }
}
