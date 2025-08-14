package budget.ui;

import javax.sql.RowSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

class DataTableModelFactoryEnum {

    static Map<Integer, Map<DDLEnum, Object>> loadSqlResultSet(RowSet rs, Set<? extends DDLEnum> columns) {
        boolean checkDDL = compareDDL(rs,columns);
        assert (checkDDL) : "mismatch between table definition in code vs db";
// TODO: 2023-09-24 Bug sql excepton when the column does not exist in the result (ie new column in enum but not in rs)
//  Actually this is caught but not reported by Assert. Better to do the check and report it as an exception?
        Map<Integer, Map<DDLEnum, Object>> dataTable = new HashMap<>();
        try {

            int rowIndex = 0;

            rs.beforeFirst();
            while (rs.next()) {
                Map<DDLEnum, Object> dataRow = new HashMap<>();

                for (DDLEnum e :
                        columns) {

                    dataRow.put(e, rs.getObject(e.getColName()));
                }
                dataTable.put(rowIndex++, dataRow);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dataTable;
    }

    private static boolean compareDDL(RowSet rs, Set<? extends DDLEnum> columns) {
        Map<String, Class<?>> resultCol = new HashMap<>();
        try {

            ResultSetMetaData md = rs.getMetaData();

            for (int i = 1; i <= md.getColumnCount(); i++) {
                resultCol.put(md.getColumnName(i), Class.forName(md.getColumnClassName(i)));
            }

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        Map<String, Class<?>> expectedCol = new HashMap<>();
        for (DDLEnum ddl : columns
        ) {
            expectedCol.put(ddl.getColName(), ddl.getClassType());
        }
        return resultCol.equals(expectedCol);
    }

}
