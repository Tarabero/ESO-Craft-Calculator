package entitites.Armor;

import entitites.CraftResource;
import entitites.Item;
import entitites.QualityType;
import entitites.Trait;
import entitites.Workbench;

public class Armor extends Item {
    private final ArmorType armorType;

    public Armor (ArmorType armorType, String name, Trait trait, QualityType qualityType, CraftResource baseCraftResource, Workbench workbench) {
        super(name, trait, qualityType, baseCraftResource, workbench);
        this.armorType = armorType;
    }

    public ArmorType getArmorType() { return armorType;}
}
