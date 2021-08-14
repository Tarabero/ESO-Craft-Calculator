package item.jewelries;

import db.entities.MaterialType;
import item.Workbench;

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

    public MaterialType getBaseMaterialType() {
        return MaterialType.BASE_JEWELRY;
    }
}
