package db.dao.interfaces;

import db.entities.Material;
import db.entities.MaterialType;

import java.util.List;

public interface MaterialDao {
    List<Material> getMaterials();
    Material getMaterialFor(MaterialType type);
}
