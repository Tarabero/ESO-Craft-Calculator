package entitites.weapon;

import entitites.*;

public class Weapon extends Item {
    private final WeaponType weaponType;

    public Weapon(WeaponType weaponType, String name, Trait trait, Material baseMaterial, Workbench workbench) {
        super(name, trait,baseMaterial, workbench);
        this.weaponType = weaponType;
    }

    public WeaponType getWeaponType() {
        return weaponType;
    }

    @Override
    public CraftResource getBaseCraftResource() {
        return new CraftResource(getBaseMaterial(), weaponType.getMaterialQuantity());
    }
}
