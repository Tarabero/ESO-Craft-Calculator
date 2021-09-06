package db.dao.interfaces;

import entities.Material;
import entities.MaterialType;

import java.util.List;

public interface MaterialDao {
    List<Material> getAllMaterials();

    Material getMaterialFor(MaterialType type);

    void setMaterial(Material material);
}
