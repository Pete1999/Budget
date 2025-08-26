package budget.ui;

import budget.data.DataProvider;
import budget.data.UserRecord;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

public class UserTableModel extends AbstractTableModel {
    private final String[] columnNames = {"ID", "Username", "Age"};
    private final Class<?>[] columnClass = {int.class,String.class, Double.class};
    private List<UserRecord> data = new ArrayList<>();

    @Override
    public int getRowCount() {
        return data.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public String getColumnName(int col) {
        return columnNames[col];
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return columnClass[columnIndex];
    }

    @Override
    public Object getValueAt(int row, int col) {
        UserRecord user = data.get(row);
        switch(col){
            case 0: return user.getId();
            case 1: return user.getUsername();
            case 2: return user.getAge();
            default: throw new IllegalArgumentException("invalid column index");
        }
    }

    void getData(DataProvider dataProvider){
        data = dataProvider.getData();
    }

    public void clearData() {
        data.clear();
        fireTableDataChanged();
    }
}