package entitites.weapon;

import entitites.Workbench;

public enum WeaponType {
    TWO_HANDED_SWORD("Two-handed sword", 140),
    TWO_HANDED_AXE("Two-handed axe", 140),
    TWO_HANDED_MAUL("Two-handed maul", 140),
    ONE_HANDED_SWORD("One-handed sword", 110),
    ONE_HANDED_AXE("One-handed axe", 110),
    ONE_HANDED_MAUL("One-handed maul", 110),
    DAGGER("Dagger", 100),
    BOW("Bow", 120),
    STAFF_FLAME("Flame staff", 120),
    STAFF_FROST("Frost staff", 120),
    STAFF_LIGHTNING("Lightning staff", 120),
    STAFF_RESTORATION("Restoration staff", 120);

    private final String name;
    private final int materialQuantity;

    WeaponType(String name, int materialQuantity) {
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
        switch (this) {
            case STAFF_RESTORATION:
            case STAFF_LIGHTNING:
            case STAFF_FROST:
            case STAFF_FLAME:
            case BOW:
                return Workbench.WOODWORKING;
            case DAGGER:
            case TWO_HANDED_SWORD:
            case ONE_HANDED_SWORD:
            case TWO_HANDED_MAUL:
            case ONE_HANDED_MAUL:
            case TWO_HANDED_AXE:
            case ONE_HANDED_AXE:
                return Workbench.SMITHING;
        }
        return null;
    }
}
