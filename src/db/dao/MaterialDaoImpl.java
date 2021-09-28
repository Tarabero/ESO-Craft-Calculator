package db.dao;

import db.DatabaseHelper;
import db.dao.interfaces.MaterialDao;
import db.parsers.MaterialParser;
import entities.Material;
import entities.MaterialType;
import util.MaterialCache;

import java.util.List;

public class MaterialDaoImpl implements MaterialDao {
    private static final String QUERY_ALL_MATERIALS = "SELECT materials.id, materials.material_name, materials.price, materials.ttc_id, material_types.material_type_name " +
            "FROM materials " +
            "JOIN material_types " +
            "ON materials.type_id = material_types.id";

    private static final String QUERY_SEARCH_MATERIAL = "SELECT materials.id, materials.material_name, materials.price, materials.ttc_id, material_types.material_type_name " +
            "FROM materials " +
            "JOIN material_types " +
            "ON materials.type_id = material_types.id " +
            "WHERE material_types.material_type_name = \"%1$s\"";

    private static final String UPDATE_MATERIAL =
            "UPDATE materials " +
                    "SET price = %1$d " +
                    "WHERE id = %2$d ;";

    private final DatabaseHelper databaseHelper;

    public MaterialDaoImpl(DatabaseHelper databaseHelper) {
        this.databaseHelper = databaseHelper;
    }

    @Override
    public List<Material> getAllMaterials() {
        return databaseHelper.executeStatementWithResult(QUERY_ALL_MATERIALS, new MaterialParser());
    }

    @Override
    public Material getMaterialFor(MaterialType type) {
        List<Material> result = databaseHelper.executeStatementWithResult(String.format(QUERY_SEARCH_MATERIAL, type.name()),
                new MaterialParser(MaterialCache.getInstance()));
        if (result != null && !result.isEmpty()) {
            return result.get(0);
        }
        return null;
    }

    @Override
    public void setMaterial(Material material) {
        databaseHelper.executeStatement(String.format(UPDATE_MATERIAL, material.getPrice(), material.getId()));
    }
}

