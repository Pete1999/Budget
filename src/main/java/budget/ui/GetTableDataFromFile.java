package budget.ui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class GetTableDataFromFile implements IGetTableData {
    String file = "test_data_budget_category.txt";
    private EnumSet rowModel = EnumSet.allOf(BudgetCategory.class);

    static Map<Integer, Map<DDLEnum, Object>> loadDataFile(String datafile, Set<? extends DDLEnum> columns) {
        Map<Integer, Map<DDLEnum, Object>> dataTable = new HashMap<>();
        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(
                        DataTableModelFactoryEnum.class.getClassLoader().getResourceAsStream(datafile)))) {
            String line;
            int rowIndex = 0;
            while ((line = reader.readLine()) != null) {
                Map<DDLEnum, Object> dataRow = new HashMap<>();
                String[] parts = line.split(",");
                int col = 0;
                for (DDLEnum e : columns){

                    dataRow.put(e, parts[col++]);
                }

                dataTable.put(rowIndex++,dataRow);
            }


        } catch (IOException e) {
            e.printStackTrace();}

        return dataTable;
    }

    @Override
    public Map<Integer, Map<DDLEnum, Object>> getData() {
        return loadDataFile(file,rowModel);
    }
}
