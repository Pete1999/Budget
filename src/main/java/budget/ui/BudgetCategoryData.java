package budget.ui;

import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Vector;

class BudgetCategoryData {
    TableModel getTableModel(){

        ArrayList<ArrayList<Object>> listData =  getDataFromFile("test_data_budget_category.txt");
        Object[][] data = new Object[listData.size()][];
        for (int i = 0; i < listData.size(); i++) {
            Object[] row = listData.get(i).toArray();
            data[i] = row;
        }

        return new DefaultTableModel(data,columnNames);
    }
     Object[][] data = {
            {"Kathy", "Smith",
                    "Snowboarding", new Integer(5), new Boolean(false)},
            {"John", "Doe",
                    "Rowing", new Integer(3), new Boolean(true)},
            {"Sue", "Black",
                    "Knitting", new Integer(2), new Boolean(false)},
            {"Jane", "White",
                    "Speed reading", new Integer(20), new Boolean(true)},
            {"Joe", "Brown",
                    "Pool", new Integer(10), new Boolean(false)}
    };

    String[] columnNames = {"category","category_type","description","target"};


    ArrayList<ArrayList<Object>> getDataFromFile(String datafile){

        ArrayList<ArrayList<Object>> table = new ArrayList<>();
        ArrayList<ArrayList<Object>> table2d = new ArrayList<>();


        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(getClass().getClassLoader().getResourceAsStream(datafile)

                )
        )
                ){
            String line;
            int rows = 0;

            while((line = reader.readLine()) != null) {
                Object[] parts = line.split(",");
                ArrayList<Object> row = new ArrayList<>(Arrays.asList(parts));
                table.add(row);
                table2d.add(rows,row);
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return table;
    }
}
