package entitites.jewelries;

import entitites.CraftResource;
import entitites.Item;
import entitites.Material;
import entitites.Trait;

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
        return super.createName() + jewelryType.toString();
    }

    @Override
    public CraftResource getBaseCraftResource() {
        return new CraftResource(getBaseMaterial(), jewelryType.getMaterialQuantity());
    }
}
