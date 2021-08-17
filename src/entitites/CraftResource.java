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

    public String toString() {
        return quantity + " " + material.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CraftResource that = (CraftResource) o;
        return getMaterial().equals(that.getMaterial());
    }

    @Override
    public int hashCode() {
        return getMaterial().hashCode();
    }
}
