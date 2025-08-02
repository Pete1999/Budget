import java.sql.*;
import java.util.*;

public class DbAccess {
    private final Connection conn;

    public DbAccess(Connection conn) {
        this.conn = conn;
    }

    // Example of type-safe call, using enums for expected columns/types
    public void callProcedure(String procName, List<DbParam<?>> params) throws SQLException {
        StringBuilder placeholders = new StringBuilder();
        for (int i = 0; i < params.size(); i++) {
            placeholders.append("?");
            if (i < params.size() - 1) placeholders.append(",");
        }
        CallableStatement stmt = conn.prepareCall("{call " + procName + "(" + placeholders + ")}");

        for (int i = 0; i < params.size(); i++) {
            DbParam<?> p = params.get(i);
            if (!p.getColumn().getClass().isEnum()) {
                throw new IllegalArgumentException("Column must be an enum value");
            }
            Class<?> expectedType = ((UserTable)p.getColumn()).getType();
            if (!expectedType.isInstance(p.getValue())) {
                throw new IllegalArgumentException(
                  "Type mismatch: " + p.getColumn() + " expects " + expectedType + " but got " + p.getValue().getClass()
                );
            }
            stmt.setObject(i+1, p.getValue());
        }
        stmt.execute();
        stmt.close();
    }

    // Fetch results type-safely
    public List<Map<UserTable, Object>> fetchUsers() throws SQLException {
        CallableStatement stmt = conn.prepareCall("{call get_all_users()}");
        ResultSet rs = stmt.executeQuery();
        List<Map<UserTable, Object>> result = new ArrayList<>();
        while (rs.next()) {
            Map<UserTable, Object> row = new EnumMap<>(UserTable.class);
            for (UserTable col : UserTable.values()) {
                Object value = rs.getObject(col.name().toLowerCase());
                if (value != null && !col.getType().isInstance(value)) {
                    throw new IllegalStateException("DB returned wrong type for " + col + ": " + value.getClass());
                }
                row.put(col, value);
            }
            result.add(row);
        }
        rs.close();
        stmt.close();
        return result;
    }
}