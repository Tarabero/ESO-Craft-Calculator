package entitites.armor;

import entitites.Workbench;

public enum ArmorType {
    LIGHT("Light"),
    MEDIUM("Medium"),
    HEAVY("Heavy");

    private String name;

    ArmorType(String name) {
        this.name = name;
    }

    public Workbench getWorkbench() {
        switch (this) {
            case LIGHT:
            case MEDIUM:
                return Workbench.CLOTHING;
            case HEAVY:
                return Workbench.SMITHING;
            default: //In case of Shield
                return Workbench.WOODWORKING;
        }
    }

    @Override
    public String toString() {
        return name;
    }
}
