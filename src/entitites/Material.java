package entitites;

public class Material extends DatabaseEntity {

    private final int id;
    private final int price;
    private final MaterialType materialType;

    public Material(int id, String name, int price, MaterialType materialType){
        super(name, id);
        this.id = id;
        this.price = price;
        this.materialType = materialType;
    }

    public int getPrice() {
        return price;
    }

    public MaterialType getMaterialType() {
        return materialType;
    }
}
