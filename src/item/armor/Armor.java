package item.armor;

import db.entities.Material;
import db.entities.Trait;
import item.CraftResource;
import item.Item;

public class Armor extends Item {
    private final ArmorSlot armorSlot;
    private final ArmorType armorType;

    public Armor(ArmorType armorType, ArmorSlot armorSlot, Trait trait, Material baseMaterial) {
        super(trait, baseMaterial);
        this.armorSlot = armorSlot;
        this.armorType = armorType;
    }

    public ArmorSlot getArmorSlot() {
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
