package budget.data;

import budget.ui.DDLEnum;

import javax.sql.RowSet;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class FakeDataTableModelFactoryEnum {
    Map<Integer, Map<DDLEnum, Object>> loadTestData(RowSet rs, Set<? extends DDLEnum> columns){
        Map<Integer, Map<DDLEnum, Object>> dataTable = new HashMap<>();
        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(
                        getClass().getClassLoader().getResourceAsStream("test_data_budget_category.txt")))) {
            String line;
            while ((line = reader.readLine()) != null) {
                Map<DDLEnum, Object> dataRow = new HashMap<>();
                String[] parts = line.split(",");
                int i = 0;
                for(DDLEnum e : columns){
                    dataRow.put(e,parts[i++]);
                }

            }



                   } catch (IOException e) {
            e.printStackTrace();
        }

        return dataTable;
    }
}
