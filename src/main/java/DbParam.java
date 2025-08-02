public class DbParam<T> {
    private final Enum<?> column;
    private final T value;

    public DbParam(Enum<?> column, T value) {
        this.column = column;
        this.value = value;
    }

    public Enum<?> getColumn() { return column; }
    public T getValue() { return value; }
}