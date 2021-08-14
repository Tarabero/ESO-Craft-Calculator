package db.entities;

public class Material extends Entity {

    private final int price;
    private final MaterialType materialType;

    public Material(int id, String name, int price, MaterialType materialType) {
        super(id, name);
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
