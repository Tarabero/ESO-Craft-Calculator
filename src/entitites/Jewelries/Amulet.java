package entitites.Jewelries;

import entitites.CraftResource;
import entitites.QualityType;
import entitites.Trait;
import entitites.Workbench;

public class Amulet extends Jewelry{
    public Amulet(JewelryType jewelryType, String name, Trait trait, QualityType qualityType, CraftResource baseCraftResource, Workbench workbench) {
        super(jewelryType, name, trait, qualityType, baseCraftResource, workbench);
    }
}
