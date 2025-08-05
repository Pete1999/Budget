package budget.ui;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

public class UserTableModel extends AbstractTableModel {
    private final String[] columnNames = {"ID", "Username", "Age"};
    private final List<Object[]> data = new ArrayList<>();

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
    public Object getValueAt(int row, int col) {
        return data.get(row)[col];
    }

    public void addRow(Integer id, String username, Integer age) {
        data.add(new Object[]{id, username, age});
        fireTableRowsInserted(data.size() - 1, data.size() - 1);
    }

    public void clearData() {
        data.clear();
        fireTableDataChanged();
    }
}