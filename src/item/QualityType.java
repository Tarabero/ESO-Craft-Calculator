package item;

public enum QualityType {
    COMMON("Common"),
    FINE("Fine"),
    RARE("Superior"),
    EPIC("Epic"),
    LEGENDARY("Legendary");

    private final String name;

    QualityType(String name) {
        this.name = name;
    }


    @Override
    public String toString() {
        return name;
    }
}
