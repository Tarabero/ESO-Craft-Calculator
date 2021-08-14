package item;

import db.entities.Material;

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

    @Override
    public String toString() {
        return material.toString() + ": " + quantity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CraftResource that = (CraftResource) o;

        return material.equals(that.material);
    }

    @Override
    public int hashCode() {
        return material.hashCode();
    }
}
