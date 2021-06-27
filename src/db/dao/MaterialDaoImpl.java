package db.dao;

import db.DatabaseHelper;
import db.dao.interfaces.MaterialDao;
import db.parsers.MaterialParser;
import entitites.Material;

import java.util.List;

public class MaterialDaoImpl implements MaterialDao {
    private static final String QUERY_ALL_MATERIALS = "SELECT materials.id, materials.material_name, materials.price, material_types.material_type_name " +
            "FROM materials " +
            "JOIN material_types " +
            "ON materials.type_id = material_types.id";

    private DatabaseHelper databaseHelper;

    public MaterialDaoImpl(DatabaseHelper databaseHelper){
        this.databaseHelper = databaseHelper;
    }

    public List<Material> getMaterials() {
        return databaseHelper.executeStatementWithResult(QUERY_ALL_MATERIALS, new MaterialParser());
    }
}
