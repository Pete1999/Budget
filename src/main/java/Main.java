import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) throws Exception {
        // Setup connection (details omitted)
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/test", "user", "pass");
        DbAccess db = new DbAccess(conn);

        // Insert example
        List<DbParam<?>> params = Arrays.asList(
            new DbParam<>(UserTable.ID, 1),
            new DbParam<>(UserTable.USERNAME, "alice"),
            new DbParam<>(UserTable.AGE, 30)
        );
        db.callProcedure("insert_user", params);

        // Fetch and print users
        List<Map<UserTable, Object>> users = db.fetchUsers();
        for (Map<UserTable, Object> user : users) {
            System.out.println(user);
        }
        conn.close();
    }
}