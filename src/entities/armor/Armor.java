package entities.armor;

import entities.CraftResource;
import entities.Item;
import entities.Material;
import entities.Trait;

public class Armor extends Item {
    private static final String ARMOR_ICON_IMAGE_DIRECTORY_PATH_BASE = "Armor/";
    private static final String FILE_SEPARATOR = "/";

    private final ArmorSlot armorSlot;
    private final ArmorType armorType;

    public Armor(ArmorType armorType, ArmorSlot armorSlot, Trait trait, Material baseMaterial) {
        super(trait, baseMaterial);
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
        StringBuilder itemIconPath = new StringBuilder(ARMOR_ICON_IMAGE_DIRECTORY_PATH_BASE);
        if (armorSlot != ArmorSlot.SHIELD) {
            itemIconPath.append(getArmorType().toString()).append(FILE_SEPARATOR);
        }
        itemIconPath.append(getArmorSlot().toString().toLowerCase());
        return itemIconPath.toString();
    }
}


