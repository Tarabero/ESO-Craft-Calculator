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

    public ArmorType getArmorType() {
        return armorType;
    }

    public ArmorSlot getArmorSlot() {
        return armorSlot;
    }

    @Override
    protected String createName() {
        return super.createName()
                + " " + armorType.toString()
                + " " + armorSlot.toString();
    }

    @Override
    public CraftResource getBaseCraftResource() {
        return new CraftResource(getBaseMaterial(), armorSlot.getMaterialQuantity());
    }

    @Override
    protected String getItemIconPath() {
        return new StringBuilder("Armor/")
                .append(getArmorType().toString())
                .append("/")
                .append(getArmorSlot().toString())
                .toString();
    }
}


