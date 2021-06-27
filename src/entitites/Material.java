package entitites;

public class Material extends Entity {

    private final long id;
    private final int price;
    private final MaterialType materialType;

    public Material(long id, String name, int price, MaterialType materialType){
        super(name);
        this.id = id;
        this.price = price;
        this.materialType = materialType;
    }

    public long getId() {
        return id;
    }

    public int getPrice() {
        return price;
    }

    public MaterialType getMaterialType() {
        return materialType;
    }
}
