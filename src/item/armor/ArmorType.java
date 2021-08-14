package item.armor;

public enum ArmorType {
    LIGHT("Light"),
    MEDIUM("Medium"),
    HEAVY("Heavy");

    private String name;

    ArmorType(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
