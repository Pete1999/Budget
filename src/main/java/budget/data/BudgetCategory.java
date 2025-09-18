package budget.data;

import budget.ui.DDLEnum;

public enum BudgetCategory implements DDLEnum {
    CATEGORY("category", String.class),
    TYPE("category_type",String.class),
    DESC("description",String.class),
    TARGET("target",Integer.class);
    
    private final String header;
    private final Class<?> clazz;

    BudgetCategory(String header, Class<?> clazz) {
        this.header = header;
        this.clazz = clazz;
    }

    @Override
    public String getColName() {
        return header;
    }

    @Override
    public Class<?> getClassType() {
        return clazz;
    }
}
