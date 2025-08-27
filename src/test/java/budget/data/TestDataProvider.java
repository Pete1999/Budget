package budget.data;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class TestDataProvider implements DataProvider {
    @Override
    public List<UserRecord> getData() {
        List<UserRecord> records = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(
                    getClass().getClassLoader().getResourceAsStream("test_data.txt")))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                records.add(new UserRecord(
                    Integer.parseInt(parts[0]),
                    parts[1],
                    Integer.parseInt(parts[2])
                ));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return records;
    }
}