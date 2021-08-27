package entities.armor;

import entities.Workbench;

public enum ArmorType {
    LIGHT("Light"),
    MEDIUM("Medium"),
    HEAVY("Heavy");

    private String name;

    ArmorType(String name) {
        this.name = name;
    }

    public Workbench getWorkbench() {
        if (this == HEAVY) {
            return Workbench.SMITHING;
        }
        return Workbench.CLOTHING;
    }

    @Override
    public String toString() {
        return name;
    }
}
