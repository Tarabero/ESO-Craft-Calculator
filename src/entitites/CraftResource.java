package entitites;

public class CraftResource {
    private int quantity;
    private final Material material;

    public CraftResource(Material material, int quantity) {
        this.quantity = quantity;
        this.material = material;
    }

    public int getTotalPrice(){
        return material.getPrice() * quantity;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Material getMaterial() {
        return material;
    }
}
