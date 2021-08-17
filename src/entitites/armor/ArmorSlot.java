package entitites.armor;

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
}
