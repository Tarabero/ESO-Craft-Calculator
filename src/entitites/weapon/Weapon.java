package entitites.weapon;

import entitites.CraftResource;
import entitites.Item;
import entitites.Material;
import entitites.Trait;

public class Weapon extends Item {
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
        return super.createName() + weaponType.toString();
    }

    @Override
    public CraftResource getBaseCraftResource() {
        return new CraftResource(getBaseMaterial(), weaponType.getMaterialQuantity());
    }
}
