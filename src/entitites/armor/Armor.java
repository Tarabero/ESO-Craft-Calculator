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
        StringBuilder name = new StringBuilder(super.createName());
        if (armorSlot != ArmorSlot.SHIELD) {
            name.append(" ").append(armorType.toString());
        }
        name.append(" ").append(armorSlot.toString());
        return name.toString();
    }

    @Override
    public CraftResource getBaseCraftResource() {
        return new CraftResource(getBaseMaterial(), armorSlot.getMaterialQuantity());
    }

    @Override
    protected String getItemIconPath() {
        StringBuilder itemIconPath = new StringBuilder("Armor/");
        if (armorSlot != ArmorSlot.SHIELD) {
            itemIconPath.append(getArmorType().toString().toLowerCase()).append("/");
        }
        itemIconPath.append(getArmorSlot().toString());
        return itemIconPath.toString();
    }
}


