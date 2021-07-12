package entitites.armor;

import entitites.*;

public class Armor extends Item {
    private final ArmorType armorType;

    public Armor(ArmorType armorType, String name, Trait trait, Material baseMaterial, Workbench workbench) {
        super(name, trait, baseMaterial, workbench);
        this.armorType = armorType;
    }

    public ArmorType getArmorType() {
        return armorType;
    }

    @Override
    public CraftResource getBaseCraftResource() {
        return new CraftResource(getBaseMaterial(), armorType.getMaterialQuantity());
    }
}
