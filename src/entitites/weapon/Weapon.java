package entitites.weapon;

import entitites.*;

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
        return getQualityType().getName()
                + " "
                + getTrait().getName()
                + " "
                + weaponType.toString();
    }

    @Override
    public CraftResource getBaseCraftResource() {
        return new CraftResource(getBaseMaterial(), weaponType.getMaterialQuantity());
    }

    @Override
    protected String getItemIconPath(Item item) {
        StringBuilder itemParameters = new StringBuilder("Weapon/");
        Weapon armor = (Weapon) item;
        itemParameters.append(armor.getWeaponType().toString());
        return itemParameters.toString();
    }
}
