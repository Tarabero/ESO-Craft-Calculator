package entitites;

public enum TraitType {
    ARMOR("Armor"),
    WEAPON("Weapon"),
    JEWELRY("Jewelry");

    private final String name;

    TraitType(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
