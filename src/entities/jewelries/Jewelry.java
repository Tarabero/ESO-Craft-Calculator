package entities.jewelries;

import entities.*;

public class Jewelry extends Item {
    private final JewelryType jewelryType;

    public Jewelry(JewelryType jewelryType, Trait trait, Material baseMaterial, Workbench workbench) {
        super(trait, baseMaterial, workbench);
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
