package db.parsers;

import entities.Material;
import entities.Trait;
import entities.TraitType;
import util.MaterialCache;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TraitParser implements Parser<Trait> {
    private final static String KEY_ID = "id";
    private final static String KEY_NAME = "trait_name";
    private final static String KEY_TYPE = "trait_type_name";

    public Trait parse(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt(KEY_ID);
        String name = resultSet.getString(KEY_NAME);
        TraitType traitType = TraitType.valueOf(resultSet.getString(KEY_TYPE));
        Material material = new MaterialParser(MaterialCache.getInstance()).parse(resultSet);
        return new Trait(name, id, traitType, material);
    }
}
