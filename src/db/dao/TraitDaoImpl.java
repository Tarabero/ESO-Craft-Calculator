package db.dao;

import db.DatabaseHelper;
import db.dao.interfaces.TraitDao;
import db.parsers.TraitParser;
import entities.Trait;
import entities.TraitType;

import java.util.List;

public class TraitDaoImpl implements TraitDao {
    private final static String QUERY_GET_BY_TYPE =
            "SELECT traits.id, traits.trait_name, materials.material_name, materials.price, materials.ttc_id, material_types.material_type_name, trait_types.trait_type_name " +
                    "FROM traits " +
                    "JOIN trait_types " +
                    "ON traits.trait_type_id = trait_types.id " +
                    "JOIN materials " +
                    "ON traits.material_id = materials.id " +
                    "JOIN material_types " +
                    "ON materials.type_id = material_types.id " +
                    "WHERE trait_type_name = \"%1$s\"";

    private DatabaseHelper databaseHelper;

    public TraitDaoImpl(DatabaseHelper databaseHelper) {
        this.databaseHelper = databaseHelper;
    }

    public List<Trait> getTraitFor(TraitType traitType) {
        String completeQuery = String.format(QUERY_GET_BY_TYPE, traitType.name());
        return databaseHelper.executeStatementWithResult(completeQuery, new TraitParser());
    }
}
