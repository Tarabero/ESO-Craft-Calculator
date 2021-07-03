package entitites.Jewelries;

import entitites.CraftResource;
import entitites.QualityType;
import entitites.Trait;
import entitites.Workbench;

public class Ring extends Jewelry{
    public Ring(JewelryType jewelryType, String name, Trait trait, QualityType qualityType, CraftResource baseCraftResource, Workbench workbench) {
        super(jewelryType, name, trait, qualityType, baseCraftResource, workbench);
    }
}
