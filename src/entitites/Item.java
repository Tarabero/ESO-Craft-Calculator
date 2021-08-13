package entitites;

import com.sun.istack.internal.Nullable;

import java.util.ArrayList;
import java.util.List;

public abstract class Item{

    private final Trait trait;
    private final Material baseMaterial;
    private final Workbench workbench;

    private QualityType qualityType;
    private List<CraftResource> qualityMaterials;

    public Item(Trait trait, Material baseMaterial, Workbench workbench) {
        this.trait = trait;
        this.baseMaterial = baseMaterial;
        this.workbench = workbench;
    }

    protected String createName(){
        StringBuilder nameBuilder = new StringBuilder();
        if (getQualityType() != null){
            nameBuilder.append(getQualityType().getName()).append(" ");
        }
        if (getTrait() != null){
            nameBuilder.append(getTrait().getName()).append(" ");
        }
        return nameBuilder.toString();
    }

    public abstract CraftResource getBaseCraftResource();

    @Nullable
    public Trait getTrait() {
        return trait;
    }

    public QualityType getQualityType() {
        if (qualityType == null){
            return QualityType.COMMON;
        }
        return qualityType;
    }

    public Workbench getWorkbench() {
        return workbench;
    }

    public List<CraftResource> getAllCraftingResources() {
        List<CraftResource> resources = new ArrayList<>();
        resources.add(getBaseCraftResource());
        resources.add(trait.getCraftResource());
        if (qualityMaterials != null) {
            resources.addAll(qualityMaterials);
        }
        return resources;
    }

    protected Material getBaseMaterial() {
        return baseMaterial;
    }

    public void setQuality(QualityType qualityType, List<CraftResource> qualityMaterials) {
        this.qualityType = qualityType;
        this.qualityMaterials = qualityMaterials;
    }

    @Override
    public String toString() {
        return createName();
    }
}

