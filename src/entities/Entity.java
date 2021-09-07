package entities;

public class Entity {
    private int id;
    protected String name;

    public Entity(String name, int id) {
        this.name = name;
        this.id = id;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return name;
    }
}
