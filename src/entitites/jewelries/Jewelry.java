package entitites.jewelries;

import entitites.*;

public class Jewelry extends Item {
    private final JewelryType jewelryType;

    public Jewelry(JewelryType jewelryType, String name, Trait trait, Material baseMaterial, Workbench workbench) {
        super(name, trait, baseMaterial, workbench);
        this.jewelryType = jewelryType;
    }

    public JewelryType getJewelryType() {
        return jewelryType;
    }

    @Override
    public CraftResource getBaseCraftResource() {
        return new CraftResource(getBaseMaterial(), jewelryType.getMaterialQuantity());
    }
}
