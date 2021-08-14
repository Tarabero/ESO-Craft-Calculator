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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Material)) return false;
        if (!super.equals(o)) return false;

        Material material = (Material) o;

        if (price != material.price) return false;
        return materialType == material.materialType;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + price;
        result = 31 * result + materialType.hashCode();
        return result;
    }
}
