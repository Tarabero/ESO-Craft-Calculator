package entities;

import java.util.ArrayList;
import java.util.List;

public abstract class Item{
    private static final String ITEM_ICON_IMAGE_PATH = "/images/Items/%s.png";

    private final Trait trait;
    private final Material baseMaterial;

    private QualityType qualityType;
    private List<CraftResource> qualityMaterials;

    public Item(Trait trait, Material baseMaterial) {
        this.trait = trait;
        this.baseMaterial = baseMaterial;

    }

    protected String createName() {
        StringBuilder nameStr = new StringBuilder();
        if(qualityType != null){
            nameStr.append(qualityType).append(" ");
        }
        if(trait != null){
            nameStr.append(trait);
        }
        return nameStr.toString();
    }

    @Override
    public String toString() {
        return createName();
    }

    public abstract CraftResource getBaseCraftResource();

    protected abstract String getItemIconPath();

    public Trait getTrait() {
        return trait;
    }

    public QualityType getQualityType() {
        return qualityType;
    }

    public List<CraftResource> getAllCraftingResources() {
        List<CraftResource> resources = new ArrayList<>();
        resources.add(getBaseCraftResource());
        resources.add(trait.getCraftResource());
        resources.addAll(qualityMaterials);
        return resources;
    }

    protected Material getBaseMaterial() {
        return baseMaterial;
    }

    public void setQuality(QualityType qualityType, List<CraftResource> qualityMaterials) {
        this.qualityType = qualityType;
        this.qualityMaterials = qualityMaterials;
    }

    public String getItemIconImagePath() {
        return String.format(ITEM_ICON_IMAGE_PATH, getItemIconPath());
    }

    public int getPrice() {
        int cost = 0;
        for (CraftResource craftResource :
                this.getAllCraftingResources()) {
            cost += craftResource.getTotalPrice();
        }
        return cost;
    }
}

