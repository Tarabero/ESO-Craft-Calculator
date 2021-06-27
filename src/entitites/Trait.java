package entitites;

public class Trait extends Entity {
    private final static int MATERIAL_REQUIRED = 1;

    private long id;
    private TraitType traitType;
    private CraftResource craftResource;

    public Trait(String name, long id, TraitType traitType, Material material) {
        super(name);
        this.id = id;
        this.traitType = traitType;
        craftResource = new CraftResource(material, MATERIAL_REQUIRED);
    }

    public int getPrice(){
        return craftResource.getTotalPrice();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public TraitType getTraitType() {
        return traitType;
    }

    public void setTraitType(TraitType traitType) {
        this.traitType = traitType;
    }

    public CraftResource getCraftResource() {
        return craftResource;
    }

    public void setCraftResource(CraftResource craftResource) {
        this.craftResource = craftResource;
    }
}
