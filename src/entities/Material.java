package entities;

public class Material extends Entity {
    private static final String MATERIAL_ICON_IMAGE_PATH = "/images/Materials/%s.png";

    private Integer price;
    private final MaterialType materialType;
    private final Integer ttcId;
    private String materialIconImagePass;

    public Material(int id, String name, Integer ttcId, int price, MaterialType materialType) {
        super(name, id);
        this.ttcId = ttcId;
        this.price = price;
        this.materialType = materialType;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Integer getTtcId() {
        return ttcId;
    }

    public MaterialType getMaterialType() {
        return materialType;
    }

    public String getMaterialName() {
        return name;
    }

    public String toString() {
        return name;
    }

    public String getMaterialIconImagePath() {
        if (materialIconImagePass == null) {
            materialIconImagePass = String.format(MATERIAL_ICON_IMAGE_PATH, getMaterialIconImageName());
        }
        return materialIconImagePass;
    }

    private String getMaterialIconImageName() {
        return toString().toLowerCase().replace(" ", "_");
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Material material = (Material) o;
        return (getId() == material.getId());
    }

    @Override
    public int hashCode() {
        int result = getId();
        result = 31 * result + materialType.hashCode();
        return result;
    }
}
