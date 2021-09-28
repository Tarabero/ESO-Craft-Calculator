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
    private final static String KEY_TTC_ID = "ttc_id";

    private MaterialCache cache = null;

    public MaterialParser(MaterialCache cache) {
        this.cache = cache;
    }

    public MaterialParser() {
    }

    public Material parse(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt(KEY_ID);
        String name = resultSet.getString(KEY_NAME);
        int price = resultSet.getInt(KEY_PRICE);
        int ttcId = resultSet.getInt(KEY_TTC_ID);
        MaterialType type = MaterialType.valueOf(resultSet.getString(KEY_MATERIAL_TYPE));

        Material materialFromDB = new Material(id, name, ttcId, price, type);
        if (cache != null) {
            if (cache.contains(materialFromDB)) {
                return cache.get(id);
            }
            cache.add(materialFromDB);
        }
        return materialFromDB;
    }
}
