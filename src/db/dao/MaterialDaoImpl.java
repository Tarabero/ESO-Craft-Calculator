package db.dao;

import db.DatabaseHelper;
import db.dao.interfaces.MaterialDao;
import db.parsers.MaterialParser;
import entitites.Material;
import entitites.MaterialType;

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

    private DatabaseHelper databaseHelper;

    public MaterialDaoImpl(DatabaseHelper databaseHelper){
        this.databaseHelper = databaseHelper;
    }

    public List<Material> getMaterials() {
        return databaseHelper.executeStatementWithResult(QUERY_ALL_MATERIALS, new MaterialParser());
    }

    @Override
    public Material getMaterialFor(MaterialType type) {
        List<Material> result = databaseHelper.executeStatementWithResult(QUERY_SEARCH_MATERIAL + type.name() + "\"", new MaterialParser());
        if (result != null && !result.isEmpty()){
            return result.get(0);
        }
        return null;
    }
}
