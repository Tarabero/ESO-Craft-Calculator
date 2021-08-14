package item.armor;

import db.entities.MaterialType;
import item.Workbench;

public enum ArmorSlot {
    HELMET("Helmet", 130),
    CHEST("Chest", 150),
    SHOULDERS("Shoulders", 130),
    BELT("Belt", 130),
    HANDS("Hands", 130),
    PANTS("Pants", 140),
    FEET("Feet", 130),
    SHIELD("Shield", 140);

    private final String name;
    private final int materialQuantity;

    ArmorSlot(String name, int materialQuantity) {
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

    public Workbench getWorkbench(ArmorType armorType) {
        if (this == SHIELD) {
            return Workbench.WOODWORKING;
        }
        switch (armorType) {
            case LIGHT:
            case MEDIUM:
                return Workbench.CLOTHING;
            case HEAVY:
                return Workbench.SMITHING;
        }
        return null;
    }

    public MaterialType getBaseMaterialType(ArmorType armorType) {
        if (this == SHIELD) {
            return MaterialType.BASE_WOOD;
        }
        switch (armorType) {
            case LIGHT:
                return MaterialType.BASE_CLOTH;
            case MEDIUM:
                return MaterialType.BASE_LEATHER;
            case HEAVY:
                return MaterialType.BASE_METAL;
        }
        return null;
    }
}
