package entitites;

public class Entity {
    private String name;

    public Entity(){}

    public Entity(String name){
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
