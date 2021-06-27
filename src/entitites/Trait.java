package entitites;

public class Trait extends DatabaseEntity {
    private final static int MATERIAL_REQUIRED = 1;

    private TraitType traitType;
    private CraftResource craftResource;

    public Trait(String name, int id, TraitType traitType, Material material) {
        super(name, id);
        this.traitType = traitType;
        craftResource = new CraftResource(material, MATERIAL_REQUIRED);
    }

    public int getPrice() {
        return craftResource.getTotalPrice();
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

    @Override
    public String toString() {
        return name + " " + traitType.name() + " " + craftResource.getMaterial().toString();
    }
}
