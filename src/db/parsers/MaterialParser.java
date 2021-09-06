package db.parsers;

import entities.Material;
import entities.MaterialType;
import util.MaterialCache;

import java.sql.ResultSet;
import java.sql.SQLException;


public class MaterialParser implements Parser<Material> {
    private final static String KEY_ID = "id";
    private final static String KEY_NAME = "material_name";
    private final static String KEY_PRICE = "price";
    private final static String KEY_MATERIAL_TYPE = "material_type_name";

    public Material parse(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt(KEY_ID);
        String name = resultSet.getString(KEY_NAME);
        MaterialType type = MaterialType.valueOf(resultSet.getString(KEY_MATERIAL_TYPE));
        int price = resultSet.getInt(KEY_PRICE);

        Material materialFromDB = new Material(id, name, price, type);
        if (MaterialCache.contains(materialFromDB)) {
            return MaterialCache.get(id);
        }
        MaterialCache.add(materialFromDB);

        return materialFromDB;
    }
}
