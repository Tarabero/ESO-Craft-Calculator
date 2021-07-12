package entitites.weapon;

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

    public String getName() {
        return name;
    }

    public int getMaterialQuantity() {
        return materialQuantity;
    }
}
