package entitites;

import entitites.armor.Armor;
import entitites.armor.ArmorSlot;
import entitites.armor.ArmorType;
import entitites.jewelries.Jewelry;
import entitites.jewelries.JewelryType;
import entitites.weapon.Weapon;
import entitites.weapon.WeaponType;

import java.util.List;

public class ItemBuilder {
    private TraitType traitType;
    private Trait trait;
    private QualityType qualityType;
    private List<CraftResource> qualityResources;
    private Object slot;
    private ArmorType armorType;
    private Material baseMaterial;

    public ItemBuilder setTraitType(TraitType traitType) {
        this.traitType = traitType;
        return this;
    }

    public ItemBuilder setTrait(Trait trait) {
        this.trait = trait;
        return this;
    }

    public ItemBuilder setQuality(QualityType qualityType, List<CraftResource> qualityResources) {
        this.qualityType = qualityType;
        this.qualityResources = qualityResources;
        return this;
    }

    public ItemBuilder setSlot(Object slot) {
        this.slot = slot;
        return this;
    }

    public ItemBuilder setArmorType(ArmorType armorType) {
        this.armorType = armorType;
        return this;
    }

    public ItemBuilder setBaseMaterial(Material baseMaterial) {
        this.baseMaterial = baseMaterial;
        return this;
    }

    public Item build() {
        return createItem();
    }

    private Item createItem() {
        Item newItem;
        switch (traitType) {
            case ARMOR:
                newItem = createArmor();
                break;
            case WEAPON:
                newItem = createWeapon();
                break;
            case JEWELRY:
                newItem = createJewelry();
                break;
            default:
                return null;
        }
        newItem.setQuality(qualityType, qualityResources);
        return newItem;
    }

    private Weapon createWeapon() {
        WeaponType weaponType = (WeaponType) slot;
        return new Weapon(weaponType, trait, baseMaterial);
    }

    private Armor createArmor() {
        return new Armor(armorType,
                (ArmorSlot) slot,
                trait,
                baseMaterial);
    }

    private Jewelry createJewelry() {
        return new Jewelry((JewelryType) slot, trait,
                baseMaterial);
    }
}
