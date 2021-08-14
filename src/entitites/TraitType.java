package entitites;

public enum TraitType {
    ARMOR("Armor"), WEAPON("Weapon"), JEWELRY("Jewelry");

    private String displayedName;

    TraitType(String displayedName) {
        this.displayedName = displayedName;
    }

    @Override
    public String toString() {
        return displayedName;
    }
}
