package budget.ui;

import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

class BudgetCategoryData extends DefaultTableModel {
    public static final String FILE_NAME = "test_data_budget_category.txt";
    private static final String[] columnNames = {"category", "category_type", "description", "target"};
    private static BudgetCategoryData instance;
    private BudgetCategoryData(){};
    private BudgetCategoryData(Object[][] data,Object[] col) {
        super(data,col);
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        Object obj = getValueAt(0,columnIndex);
        return obj.getClass();
    }

    static BudgetCategoryData getInstance(){
        if (instance == null){
            instance = BudgetCategoryData.getTableModel();
        }
        return instance;
    }
    static BudgetCategoryData getTableModel() {

        ArrayList<ArrayList<Object>> listData = getDataFromFile();
        Object[][] data = new Object[listData.size()][];
        for (int i = 0; i < listData.size(); i++) {
            Object[] row = listData.get(i).toArray();
            data[i] = row;
        }

        return new BudgetCategoryData(data, columnNames);
    }

    private static ArrayList<ArrayList<Object>> getDataFromFile() {

        ArrayList<ArrayList<Object>> table = new ArrayList<>();


        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(BudgetCategoryData.class.getClassLoader().getResourceAsStream(FILE_NAME)

                )
        )
        ) {
            String line;

            while ((line = reader.readLine()) != null) {
                String[] part = line.split(",");
                int parts = part.length;
                Object[] rowArray = new Object[parts];
                int target = Integer.parseInt(part[3]);
                for(int col = 0; col < parts; col++){
                    if (col == 3){
                        rowArray[col] = Integer.parseInt(part[col]);
                    }else{
                        rowArray[col] = part[col];
                    }
                }
                ArrayList<Object> row = new ArrayList<>(Arrays.asList(rowArray));

                table.add(row);

            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return table;
    }
}
