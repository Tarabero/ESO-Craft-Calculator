package db.entities;

public class Entity {
    private int id;
    private String name;

    public Entity(int id, String name) {
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
