public enum UserTable {
    ID(Integer.class),
    USERNAME(String.class),
    AGE(Integer.class);

    private final Class<?> type;

    UserTable(Class<?> type) {
        this.type = type;
    }
    
    public Class<?> getType() {
        return type;
    }
}