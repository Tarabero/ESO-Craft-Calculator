package gui.dialog.newitem;

import entities.*;
import entities.armor.Armor;
import entities.armor.ArmorSlot;
import entities.armor.ArmorType;
import entities.jewelries.Jewelry;
import entities.jewelries.JewelryType;
import entities.weapon.Weapon;
import entities.weapon.WeaponType;

import java.util.List;

public class ItemBuilder {

    private TraitType itemType;
    private Object itemSlot;
    private ArmorType armorType;
    private Trait itemTrait;
    private Material itemBaseMaterial;
    private QualityType itemQuality;
    private List<CraftResource> itemQualityMaterials;

    public ItemBuilder setItemType(TraitType itemType) {
        this.itemType = itemType;
        return this;
    }

    public ItemBuilder setItemSlot(Object itemSlot) {
        this.itemSlot = itemSlot;
        return this;
    }

    public ItemBuilder setArmorType(ArmorType armorType) {
        this.armorType = armorType;
        return this;
    }

    public ItemBuilder setItemTrait(Trait itemTrait) {
        this.itemTrait = itemTrait;
        return this;
    }

    public ItemBuilder setItemBaseMaterial(Material itemBaseMaterial) {
        this.itemBaseMaterial = itemBaseMaterial;
        return this;
    }

    public ItemBuilder setItemQuality(QualityType itemQuality, List<CraftResource> itemQualityMaterials) {
        this.itemQualityMaterials = itemQualityMaterials;
        this.itemQuality = itemQuality;
        return this;
    }

    public Item buildItem() {
        Item newItem = createNewItem();
        newItem.setQuality(itemQuality, itemQualityMaterials);
        return newItem;
    }

    private Item createNewItem() {
        switch (itemType) {
            case ARMOR:
                return createNewArmor();
            case WEAPON:
                return createNewWeapon();
            case JEWELRY:
                return createNewJewelry();
        }
        return null;
    }

    private Armor createNewArmor() {
        return new Armor(
                armorType,
                (ArmorSlot) itemSlot,
                itemTrait,
                itemBaseMaterial
        );
    }

    private Weapon createNewWeapon() {
        return new Weapon(
                (WeaponType) itemSlot,
                itemTrait,
                itemBaseMaterial
        );
    }

    private Jewelry createNewJewelry() {
        return new Jewelry(
                (JewelryType) itemSlot,
                itemTrait,
                itemBaseMaterial
        );
    }
}
