package entitites;

public class Item extends Entity {

    private final Trait trait;
    private final QualityType qualityType;
    private final CraftResource baseCraftResource;
    private final Workbench workbench;

    public Item (String name, Trait trait, QualityType qualityType, CraftResource baseCraftResource, Workbench workbench){
        super(name);
        this.trait = trait;
        this.qualityType = qualityType;
        this.baseCraftResource = baseCraftResource;
        this.workbench = workbench;
    }

    public Trait getTrait() { return trait;}

    public QualityType getQualityType() {return qualityType;}

    public CraftResource getBaseCraftResource() {return baseCraftResource;}

    public Workbench getWorkbench() {return workbench;}

}

