package entitites.armor;

import entitites.*;

public class Armor extends Item {
    private final ArmorSlot armorSlot;
    private final ArmorType armorType;

    public Armor(ArmorType armorType, ArmorSlot armorSlot, Trait trait, Material baseMaterial, Workbench workbench) {
        super(trait, baseMaterial, workbench);
        this.armorSlot = armorSlot;
        this.armorType = armorType;
    }

    public ArmorSlot getArmorType() {
        return armorSlot;
    }

    @Override
    protected String createName() {
        return getQualityType().getName()
                + " "
                + getTrait().getName()
                + " "
                + armorType.getName()
                + " "
                + armorSlot.getName();
    }

    @Override
    public CraftResource getBaseCraftResource() {
        return new CraftResource(getBaseMaterial(), armorSlot.getMaterialQuantity());
    }
}
