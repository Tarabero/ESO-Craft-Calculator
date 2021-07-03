package entitites.Jewelries;

import entitites.CraftResource;
import entitites.Item;
import entitites.QualityType;
import entitites.Trait;
import entitites.Workbench;

public class Jewelry extends Item {
    private final JewelryType jewelryType;

    public Jewelry (JewelryType jewelryType, String name, Trait trait, QualityType qualityType, CraftResource baseCraftResource, Workbench workbench) {
        super(name, trait, qualityType, baseCraftResource, workbench);
        this.jewelryType = jewelryType;
    }

    public JewelryType getArmorType() { return jewelryType;}
}
