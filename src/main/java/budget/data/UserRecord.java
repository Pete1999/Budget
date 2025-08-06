package budget.data;

public class UserRecord {
    private final int id;
    private final String username;
    private final int age;

    public UserRecord(int id, String username, int age) {
        this.id = id;
        this.username = username;
        this.age = age;
    }

    public int getId() { return id; }
    public String getUsername() { return username; }
    public int getAge() { return age; }
}