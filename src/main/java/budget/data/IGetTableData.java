package budget.data;

import budget.model.DDLEnum;

import java.util.Map;

public interface IGetTableData {
    Map<Integer, Map<DDLEnum, Object>> getData();
}
