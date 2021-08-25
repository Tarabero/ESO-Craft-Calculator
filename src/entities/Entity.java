package entities;

public class Entity {
    protected String name;

    public Entity(){}

    public Entity(String name){
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
