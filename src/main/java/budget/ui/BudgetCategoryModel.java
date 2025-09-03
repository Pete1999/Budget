package budget.ui;

import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.AbstractTableModel;
import java.util.*;

public class BudgetCategoryModel extends AbstractTableModel implements TableModelListener  {
    private final IDataTableModelEnum<BudgetCategory> dtmEnum;
    private final Set<BudgetCategory> editableColumns = EnumSet.allOf(BudgetCategory.class);
    private final Set<Integer> rowsChanged = new HashSet<>();
    private final List<DataRow<BudgetCategory>> inserts = new ArrayList<>();


    BudgetCategoryModel(IDataTableModelEnum<BudgetCategory> dtmEnum) {
        // TODO: 2023-05-10 set table data on creating an instance?
        this.dtmEnum = dtmEnum;
        setTableData();
        this.addTableModelListener(this);


    }

    private void setTableData() {
        dtmEnum.setTableData();
    }

    // TODO: 2022-03-31 Do this using the new DataTableModel, no Row class
    List<String> listBudgetCategory() {

        ArrayList<String> categoryName = new ArrayList<>();
        for (int i = 0; i < dtmEnum.getNumRows(); i++) {
            String category = dtmEnum.getData(i,BudgetCategory.CATEGORY,String.class);
            categoryName.add(category);
        }

        return categoryName;
    }
    boolean saveChanges(){
        boolean result;
        saveUpdates();
        result = saveInserts();
        return result;
    }
    private void saveUpdates() {
        for (int i :
                rowsChanged) {
            String category = dtmEnum.getData(i,BudgetCategory.CATEGORY,String.class);
            String type = dtmEnum.getData(i,BudgetCategory.TYPE,String.class);
            String desc = dtmEnum.getData(i,BudgetCategory.DESC,String.class);
            int target = dtmEnum.getData(i,BudgetCategory.TARGET,Integer.class);
            DataRow<BudgetCategory> transactionsUpdate = new DataRow<>(new EnumMap<>(BudgetCategory.class));
            transactionsUpdate.putData(BudgetCategory.CATEGORY, category);
            transactionsUpdate.putData(BudgetCategory.TYPE, type);
            transactionsUpdate.putData(BudgetCategory.DESC, desc);
            transactionsUpdate.putData(BudgetCategory.TARGET, target);

            Db.updateBudgetCategory(transactionsUpdate);
        }
        rowsChanged.clear();
    }

    private boolean saveInserts(){
        boolean ret = false;

        for (DataRow<BudgetCategory> bc : inserts
             ) {

            Db.insertBudgetCategory(bc);
            ret = true;
        }
        inserts.clear();
        return ret;
    }

    int addRow(DataRow<BudgetCategory> budget){
        inserts.add(budget);
        return inserts.size();
    }

    @Override
    public int getRowCount() {
        return dtmEnum.getNumRows();
    }

    @Override
    public int getColumnCount() {
        return dtmEnum.getNumColumns();
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return dtmEnum.getData(rowIndex, columnIndex);
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int colIndex) {
        dtmEnum.setData(aValue, rowIndex, colIndex);
        fireTableCellUpdated(rowIndex,colIndex);

    }
    @Override
    public String getColumnName(int columnIndex) {
        return dtmEnum.findColEnum(columnIndex).getColName();
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return dtmEnum.findColEnum(columnIndex).getClassType();
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        for (BudgetCategory col : editableColumns) {
            if (dtmEnum.findColEnum(columnIndex) == col) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void tableChanged(TableModelEvent e) {
        //Check that only one row is changed when this event is fired, and that all editable columns are changed

        final int firstRow = e.getFirstRow();
        final int lastRow = e.getLastRow();
        final int type = e.getType();
        if ((firstRow != lastRow) || (type != 0))
            throw new IllegalArgumentException("Expect one row to be updated, got first/" + firstRow
                    + " last/" + lastRow + " type/" + type);
        int rowChangedForUpdate = e.getFirstRow();
        rowsChanged.add(rowChangedForUpdate);

    }

}
