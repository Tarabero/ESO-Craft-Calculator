package db.dao.interfaces;

import entities.Material;
import entities.MaterialType;

import java.util.List;

public interface MaterialDao {
    List<Material> getMaterials();
    Material getMaterialFor(MaterialType type);

    void setMaterial(Material material);
}
