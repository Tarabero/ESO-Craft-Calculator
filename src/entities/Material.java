package entities;

public class Material extends Entity {

    private Integer price;
    private final MaterialType materialType;
    private String materialIconImagePass;

    public Material(int id, String name, int price, MaterialType materialType) {
        super(name, id);
        this.price = price;
        this.materialType = materialType;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
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
            // TODO: 16.08.2021 UNCOMMENT NEXT STRING WHEN PROPER ICONS WILL BE CREATED
//            materialIconImagePass = "/images/Materials/"+getMaterialIconImageName()+".png";
            // TODO: 16.08.2021 DELETE NEXT STRING WHEN PROPER ICONS WILL BE CREATED
            materialIconImagePass = "/images/Materials/DefaultIcon.png";
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
