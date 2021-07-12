package entitites.armor;

public enum ArmorType {
    LIGHT("Light"), MEDIUM("Medium"), HEAVY("Heavy");

    private String name;

    ArmorType(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
