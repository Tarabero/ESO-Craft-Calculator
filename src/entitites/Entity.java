package entitites;

public class Entity {
    protected String name;

    public Entity(){}

    public Entity(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name;
    }
}
