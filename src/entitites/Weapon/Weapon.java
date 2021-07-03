package entitites.Weapon;

import entitites.CraftResource;
import entitites.Item;
import entitites.QualityType;
import entitites.Trait;
import entitites.Workbench;

public class Weapon extends Item {
    private final WeaponType weaponType;

    public Weapon (WeaponType weaponType, String name, Trait trait, QualityType qualityType, CraftResource baseCraftResource, Workbench workbench) {
        super(name, trait, qualityType, baseCraftResource, workbench);
        this.weaponType = weaponType;
    }

    public WeaponType getArmorType() { return weaponType;}

}
