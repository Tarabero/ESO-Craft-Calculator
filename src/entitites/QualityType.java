package entitites;

public enum QualityType {
    FINE("Fine"),
    RARE("Superior"),
    EPIC("Epic"),
    LEGENDARY("Legendary");

    private final String name;

    QualityType(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
