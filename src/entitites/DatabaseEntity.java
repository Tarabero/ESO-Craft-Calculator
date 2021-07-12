package entitites;

public class DatabaseEntity extends Entity {
    private int id;

    public DatabaseEntity(String name, int id) {
        super(name);
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
