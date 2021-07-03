package entitites.Weapon;

import entitites.CraftResource;
import entitites.QualityType;
import entitites.Trait;
import entitites.Workbench;

public class Wooden extends Weapon{
    public Wooden(WeaponType weaponType, String name, Trait trait, QualityType qualityType, CraftResource baseCraftResource, Workbench workbench) {
        super(weaponType, name, trait, qualityType, baseCraftResource, workbench);
    }
}
