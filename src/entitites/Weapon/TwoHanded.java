package entitites.Weapon;

import entitites.CraftResource;
import entitites.QualityType;
import entitites.Trait;
import entitites.Workbench;

public class TwoHanded extends Weapon{
    public TwoHanded(WeaponType weaponType, String name, Trait trait, QualityType qualityType, CraftResource baseCraftResource, Workbench workbench) {
        super(weaponType, name, trait, qualityType, baseCraftResource, workbench);
    }
}
