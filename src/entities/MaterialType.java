package entities;

import entities.armor.ArmorSlot;
import entities.armor.ArmorType;
import entities.jewelries.JewelryType;
import entities.weapon.WeaponType;

public enum MaterialType {
    TRAIT_ARMOR("Trait Armor"),
    TRAIT_WEAPON("Trait Weapon"),
    TRAIT_JEWELRY("Trait Jewelry"),
    BASE_CLOTH("Basic Clothing Light"),
    BASE_LEATHER("Basic Clothing Medium"),
    BASE_METAL("Basic Metalwork"),
    BASE_JEWELRY("Basic Jewelry"),
    BASE_WOOD("Basic Woodwork"),
    FINE_CLOTHING("Upgrade Clothing Fine"),
    RARE_CLOTHING("Upgrade Clothing Superior"),
    EPIC_CLOTHING("Upgrade Clothing Epic"),
    LEGENDARY_CLOTHING("Upgrade Clothing Legendary"),
    FINE_SMITHING("Upgrade Metalwork Fine"),
    RARE_SMITHING("Upgrade Metalwork Superior"),
    EPIC_SMITHING("Upgrade Metalwork Epic"),
    LEGENDARY_SMITHING("Upgrade Metalwork Legendary"),
    FINE_JEWELRY("Upgrade Jewelry Fine"),
    RARE_JEWELRY("Upgrade Jewelry Superior"),
    EPIC_JEWELRY("Upgrade Jewelry Epic"),
    LEGENDARY_JEWELRY("Upgrade Jewelry Legendary"),
    FINE_WOODWORK("Upgrade Woodwork Fine"),
    RARE_WOODWORK("Upgrade Woodwork Superior"),
    EPIC_WOODWORK("Upgrade Woodwork Epic"),
    LEGENDARY_WOODWORK("Upgrade Woodwork Legendary");

    private final String name;

    MaterialType(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }

    public static MaterialType getBaseMaterialTypeFor(ArmorType armorType, ArmorSlot armorSlot) {
        if (armorSlot == ArmorSlot.SHIELD) {
            return BASE_WOOD;
        }
        switch (armorType) {
            case LIGHT:
                return BASE_CLOTH;
            case MEDIUM:
                return BASE_LEATHER;
            case HEAVY:
                return BASE_METAL;
        }
        return null;
    }

    public static MaterialType getBaseMaterialTypeFor(WeaponType weaponType) {
        switch (weaponType) {
            case BOW:
            case STAFF_FLAME:
            case STAFF_FROST:
            case STAFF_LIGHTNING:
            case STAFF_RESTORATION:
                return BASE_WOOD;
            case ONE_HANDED_AXE:
            case TWO_HANDED_AXE:
            case ONE_HANDED_MAUL:
            case TWO_HANDED_MAUL:
            case ONE_HANDED_SWORD:
            case TWO_HANDED_SWORD:
            case DAGGER:
                return BASE_METAL;
        }
        return null;
    }

    public static MaterialType getBaseMaterialTypeFor(JewelryType jewelryType) {
        return BASE_JEWELRY;
    }
}


