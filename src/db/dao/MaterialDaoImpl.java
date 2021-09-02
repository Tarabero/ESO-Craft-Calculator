package db.dao;

import db.DatabaseHelper;
import db.dao.interfaces.MaterialDao;
import db.parsers.MaterialParser;
import entities.Material;
import entities.MaterialType;

import java.util.List;

public class MaterialDaoImpl implements MaterialDao {
    private static final String QUERY_ALL_MATERIALS = "SELECT materials.id, materials.material_name, materials.price, material_types.material_type_name " +
            "FROM materials " +
            "JOIN material_types " +
            "ON materials.type_id = material_types.id";

    private static final String QUERY_SEARCH_MATERIAL = "SELECT materials.id, materials.material_name, materials.price, material_types.material_type_name " +
            "FROM materials " +
            "JOIN material_types " +
            "ON materials.type_id = material_types.id " +
            "WHERE material_types.material_type_name = \"";

    private static final String UPDATE_MATERIAL_SELECT_AND_SET =
            //"SELECT materials.id, materials.material_name, materials.price " +
            //"FROM materials; " +
            "UPDATE materials " +
                    "SET price = \"";
    private static final String UPDATE_MATERIAL_WHERE =
            "WHERE id = \"";


    private DatabaseHelper databaseHelper;

    public MaterialDaoImpl(DatabaseHelper databaseHelper) {
        this.databaseHelper = databaseHelper;
    }

    public List<Material> getMaterials() {
        return databaseHelper.executeStatementWithResult(QUERY_ALL_MATERIALS, new MaterialParser());
    }

    @Override
    public Material getMaterialFor(MaterialType type) {
        List<Material> result = databaseHelper.executeStatementWithResult(QUERY_SEARCH_MATERIAL + type.name() + "\"", new MaterialParser());
        if (result != null && !result.isEmpty()) {
            return result.get(0);
        }
        return null;
    }

    @Override
    public void setMaterial(Material material) {
        databaseHelper.executeStatement(UPDATE_MATERIAL_SELECT_AND_SET
                + material.getPrice() + "\""
                + UPDATE_MATERIAL_WHERE
                + material.getId() + "\";");
    }
}
