package entitites.armor;

import entitites.CraftResource;
import entitites.Item;
import entitites.Material;
import entitites.Trait;

public class Armor extends Item {
    private final ArmorSlot armorSlot;
    private final ArmorType armorType;

    public Armor(ArmorType armorType, ArmorSlot armorSlot, Trait trait, Material baseMaterial) {
        super(trait, baseMaterial);
        this.armorSlot = armorSlot;
        this.armorType = armorType;
    }

    public ArmorSlot getArmorType() {
        return armorSlot;
    }

    @Override
    protected String createName() {
        String slot = armorSlot != ArmorSlot.SHIELD ? armorType.toString() + " " : "";
        return super.createName()
                + slot
                + armorSlot.toString();
    }

    @Override
    public CraftResource getBaseCraftResource() {
        return new CraftResource(getBaseMaterial(), armorSlot.getMaterialQuantity());
    }
}
