package budget.ui;

import javax.sql.rowset.CachedRowSet;
import javax.sql.rowset.RowSetFactory;
import javax.sql.rowset.RowSetProvider;
import javax.swing.*;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

public class Db {

    private static final Connection conn = getConnection();

    static Connection getConnection() {
        Connection c = null;

        Properties dbProp = loadProperties("database.properties");

        try {

            assert dbProp != null;
            c = DriverManager.getConnection(dbProp.getProperty("db.conn.url"), dbProp);
            c.setAutoCommit(false);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return c;
    }

    static Properties loadProperties(String fileName) {
        Properties prop = new Properties();
        InputStream isDbPropertiesFile;
        try {
            isDbPropertiesFile = Class.forName("fun.shandbox.Db").getClassLoader().getResourceAsStream(fileName);
            prop.load(isDbPropertiesFile);
        } catch (ClassNotFoundException | IOException e) {
            e.printStackTrace();
            return null;
        }
        return prop;
    }

    static CachedRowSet getBudgetCategoryCategory() {
        final String call = "{ ? = call getall_budget_category_category() }";
        CachedRowSet crs = null;

        try (CallableStatement stmt = conn.prepareCall(call)) {
            stmt.registerOutParameter(1, Types.OTHER);
            stmt.execute();
            ResultSet rs = (ResultSet) stmt.getObject(1);
            RowSetFactory factory = RowSetProvider.newFactory();
            crs = factory.createCachedRowSet();
            crs.populate(rs);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return crs;
    }

    static String updateBudgetCategory(DataRow<BudgetCategory> updateRow) {
        final String call = "{call update_budget_category(?,?,?,?)}";
        String ret = "err";
        try (CallableStatement cs = conn.prepareCall(call)) {
            cs.registerOutParameter(1, Types.VARCHAR);
            cs.setString(1, updateRow.getData(BudgetCategory.CATEGORY, String.class));
            cs.setString(2, updateRow.getData(BudgetCategory.TYPE, String.class));
            cs.setString(3, updateRow.getData(BudgetCategory.DESC, String.class));
            cs.setInt(4, updateRow.getData(BudgetCategory.TARGET, Integer.class));

            cs.execute();
            ret = cs.getString(1);
            conn.commit();
        } catch (SQLException throwables) {
            handleError(throwables);
        }
        return ret;
    }

    // TODO: 2023-05-10 BUG this and the update of Budget Category leave some changes commited and others rolled back o
    //  on error. Sort of ok since we could just save and comit each data entry as it is.
    static String insertBudgetCategory(DataRow<BudgetCategory> insert) {
        String ret = "err";
        final String call = "{call insert_budget_category(?,?,?,?)}";
        try (CallableStatement cs = conn.prepareCall(call);
             AutoCloseable ignored = conn::rollback) {
            cs.registerOutParameter(1, Types.VARCHAR);
            cs.setString(1, insert.getData(BudgetCategory.CATEGORY, String.class));
            cs.setString(2, insert.getData(BudgetCategory.TYPE, String.class));
            cs.setString(3, insert.getData(BudgetCategory.DESC, String.class));
            cs.setInt(4, insert.getData(BudgetCategory.TARGET, Integer.class));
            cs.execute();
            ret = cs.getString(1);
            conn.commit();
        } catch (Exception throwables) {
            handleError(throwables);
        }
        return ret;
    }

    private static void handleError(Exception throwables) {
            /*
            TODO: See this for great use of try with resources block (partly done but nice idea to catch sql exception only
             2023-05-03 https://stackoverflow.com/questions/40785714/what-is-the-best-way-to-rollback-a-statement-execution-failure-in-java-8
             */
        throwables.printStackTrace();
        JOptionPane.showConfirmDialog(null, throwables.getMessage(), "Error", JOptionPane.YES_NO_OPTION);
    }
}
