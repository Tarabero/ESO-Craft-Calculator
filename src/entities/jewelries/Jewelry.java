package entities.jewelries;

import entities.CraftResource;
import entities.Item;
import entities.Material;
import entities.Trait;

public class Jewelry extends Item {
    private final JewelryType jewelryType;

    public Jewelry(JewelryType jewelryType, Trait trait, Material baseMaterial) {
        super(trait, baseMaterial);
        this.jewelryType = jewelryType;
    }

    public JewelryType getJewelryType() {
        return jewelryType;
    }

    @Override
    protected String createName() {
        return getQualityType().toString()
                + " "
                + getTrait().toString()
                + " "
                + jewelryType.toString();
    }

    @Override
    public CraftResource getBaseCraftResource() {
        return new CraftResource(getBaseMaterial(), jewelryType.getMaterialQuantity());
    }

    @Override
    protected String getItemIconPath() {
        return "Jewelry/" + getJewelryType().toString().toLowerCase();
    }
}
