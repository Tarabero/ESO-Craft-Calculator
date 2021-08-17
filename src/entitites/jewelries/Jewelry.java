package entitites.jewelries;

import entitites.*;

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
        return getQualityType().getName()
                + " "
                + getTrait().getName()
                + " "
                + jewelryType.toString();
    }

    @Override
    public CraftResource getBaseCraftResource() {
        return new CraftResource(getBaseMaterial(), jewelryType.getMaterialQuantity());
    }

    @Override
    protected String getItemIconPath(Item item) {
        StringBuilder itemParameters = new StringBuilder("Jewelry/");
        Jewelry jewelry = (Jewelry) item;
        itemParameters.append(jewelry.getJewelryType().toString());
        return itemParameters.toString();
    }
}
