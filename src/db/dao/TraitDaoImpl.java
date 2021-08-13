package db.dao;

import db.DatabaseHelper;
import db.dao.interfaces.TraitDao;
import db.parsers.TraitParser;
import entitites.Trait;
import entitites.TraitType;

import java.util.List;

public class TraitDaoImpl implements TraitDao {
    private final static String QUERY_GET_BY_TYPE =
            "SELECT traits.id, traits.trait_name, materials.id AS material_id, materials.material_name, materials.price, material_types.material_type_name, trait_types.trait_type_name " +
                    "FROM traits " +
                    "JOIN trait_types " +
                    "ON traits.trait_type_id = trait_types.id " +
                    "JOIN materials " +
                    "ON traits.material_id = materials.id " +
                    "JOIN material_types " +
                    "ON materials.type_id = material_types.id " +
                    "WHERE trait_type_name = ";

    private DatabaseHelper databaseHelper;

    public TraitDaoImpl(DatabaseHelper databaseHelper) {
        this.databaseHelper = databaseHelper;
    }

    public List<Trait> getTraitsFor(TraitType traitType) {
        String completeQuery = QUERY_GET_BY_TYPE + "\"" + traitType.name() + "\"";
        return databaseHelper.executeStatementWithResult(completeQuery, new TraitParser());
    }
}
