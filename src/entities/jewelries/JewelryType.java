package entities.jewelries;

import entities.Workbench;

public enum JewelryType {
    RING("Ring", 100),
    AMULET("Amulet", 150);

    private final String name;
    private final int materialQuantity;

    JewelryType(String name, int materialQuantity) {
        this.name = name;
        this.materialQuantity = materialQuantity;
    }

    @Override
    public String toString() {
        return name;
    }

    public int getMaterialQuantity() {
        return materialQuantity;
    }

    public Workbench getWorkbench() {
        return Workbench.JEWELRY;
    }
}
