package entitites;

import java.util.ArrayList;
import java.util.List;

public abstract class Item extends Entity {

    private final Trait trait;
    private final Material baseMaterial;
    private final Workbench workbench;

    private QualityType qualityType;
    private List<CraftResource> qualityMaterials;

    public Item(Trait trait, Material baseMaterial, Workbench workbench) {
        this.trait = trait;
        this.baseMaterial = baseMaterial;
        this.workbench = workbench;
        this.name = createName();
    }

    protected abstract String createName();

    public abstract CraftResource getBaseCraftResource();

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

    public void setQuality(QualityType qualityType, List<CraftResource> qualityMaterials) {
        this.qualityType = qualityType;
        this.qualityMaterials = qualityMaterials;
    }
}

