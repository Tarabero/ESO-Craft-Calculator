package entities.weapon;

import entities.CraftResource;
import entities.Item;
import entities.Material;
import entities.Trait;

public class Weapon extends Item {
    private static final String WEAPON_ICON_IMAGE_DIRECTORY_PATH_BASE = "Weapon/";

    private final WeaponType weaponType;

    public Weapon(WeaponType weaponType, Trait trait, Material baseMaterial) {
        super(trait, baseMaterial);
        this.weaponType = weaponType;
    }

    public WeaponType getWeaponType() {
        return weaponType;
    }

    @Override
    protected String createName() {
        return getQualityType().toString()
                + " "
                + getTrait().toString()
                + " "
                + weaponType.toString();
    }

    @Override
    public CraftResource getBaseCraftResource() {
        return new CraftResource(getBaseMaterial(), weaponType.getMaterialQuantity());
    }

    @Override
    protected String getItemIconPath() {
        return WEAPON_ICON_IMAGE_DIRECTORY_PATH_BASE + getWeaponType().toString().toLowerCase().replace(" ", "_");
    }
}
