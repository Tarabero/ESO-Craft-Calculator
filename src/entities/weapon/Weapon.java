package entities.weapon;

import entities.*;

public class Weapon extends Item {
    private final WeaponType weaponType;

    public Weapon(WeaponType weaponType, Trait trait, Material baseMaterial, Workbench workbench) {
        super(trait,baseMaterial, workbench);
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
        return "Weapon/" + getWeaponType().toString().toLowerCase().replace(" ", "_");
    }
}
