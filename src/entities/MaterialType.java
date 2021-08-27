package entities;

import entities.armor.ArmorSlot;
import entities.armor.ArmorType;
import entities.jewelries.JewelryType;
import entities.weapon.WeaponType;

public enum MaterialType {
    TRAIT_ARMOR,
    TRAIT_WEAPON,
    TRAIT_JEWELRY,
    BASE_CLOTH,
    BASE_LEATHER,
    BASE_METAL,
    BASE_JEWELRY,
    BASE_WOOD,
    FINE_CLOTHING,
    RARE_CLOTHING,
    EPIC_CLOTHING,
    LEGENDARY_CLOTHING,
    FINE_SMITHING,
    RARE_SMITHING,
    EPIC_SMITHING,
    LEGENDARY_SMITHING,
    FINE_JEWELRY,
    RARE_JEWELRY,
    EPIC_JEWELRY,
    LEGENDARY_JEWELRY,
    FINE_WOODWORK,
    RARE_WOODWORK,
    EPIC_WOODWORK,
    LEGENDARY_WOODWORK;

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


