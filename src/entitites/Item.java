package entitites;

import java.util.ArrayList;
import java.util.List;

public abstract class Item{

    private final Trait trait;
    private final Material baseMaterial;
    private final Workbench workbench;

    private QualityType qualityType;
    private List<CraftResource> qualityMaterials;
    private String itemIconImagePass;

    public Item(Trait trait, Material baseMaterial, Workbench workbench) {
        this.trait = trait;
        this.baseMaterial = baseMaterial;
        this.workbench = workbench;
    }

    protected String createName() {
        StringBuilder nameStr = new StringBuilder("");
        if(qualityType != null){
            nameStr.append(qualityType.getName()).append(" ");
        }
        if(trait != null){
            nameStr.append(trait.getName());
        }
        return nameStr.toString();
    }

    @Override
    public String toString() {
        return createName();
    }

    public abstract CraftResource getBaseCraftResource();

    protected abstract String getItemIconPath(Item item);

    public Trait getTrait() {
        return trait;
    }

    public QualityType getQualityType() {
        return qualityType;
    }

    public Workbench getWorkbench() {
        return workbench;
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

    public void setQualityAndCreateName(QualityType qualityType, List<CraftResource> qualityMaterials) {
        this.qualityType = qualityType;
        this.qualityMaterials = qualityMaterials;
    }

    public String getItemIconImagePath() {
        return "images/Items/" + getItemIconPath(this) + ".png";
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

