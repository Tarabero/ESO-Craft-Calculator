package entitites.Armor;

import entitites.CraftResource;
import entitites.QualityType;
import entitites.Trait;
import entitites.Workbench;

public class Pants extends Armor {
    public Pants(ArmorType armorType, String name, Trait trait, QualityType qualityType, CraftResource baseCraftResource, Workbench workbench) {
        super(armorType, name, trait, qualityType, baseCraftResource, workbench);
    }
}
