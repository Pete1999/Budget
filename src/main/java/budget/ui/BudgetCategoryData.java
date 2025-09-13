package budget.ui;

import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

class BudgetCategoryData {
    TableModel getTableModel(){

        ArrayList<ArrayList<Object>> listData =  getDataFromFile();
        Object[][] data = new Object[listData.size()][];
        for (int i = 0; i < listData.size(); i++) {
            Object[] row = listData.get(i).toArray();
            data[i] = row;
        }

        return new DefaultTableModel(data,columnNames);
    }

    String[] columnNames = {"category","category_type","description","target"};


    ArrayList<ArrayList<Object>> getDataFromFile(){

        ArrayList<ArrayList<Object>> table = new ArrayList<>();


        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(getClass().getClassLoader().getResourceAsStream("test_data_budget_category.txt")

                )
        )
                ){
            String line;

            while((line = reader.readLine()) != null) {
                Object[] parts = line.split(",");
                ArrayList<Object> row = new ArrayList<>(Arrays.asList(parts));
                table.add(row);

            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return table;
    }
}
