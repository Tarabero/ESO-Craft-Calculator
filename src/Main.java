import db.DatabaseHelper;
import db.dao.MaterialDaoImpl;
import db.dao.TraitDaoImpl;
import db.dao.interfaces.MaterialDao;
import db.dao.interfaces.TraitDao;
import entitites.Material;
import entitites.Trait;
import entitites.TraitType;

import java.util.List;

public class Main {

    public static void main(String[] args) {
        DatabaseHelper databaseHelper = DatabaseHelper.getInstance();
        databaseHelper.connect();
        MaterialDao materialDao = new MaterialDaoImpl(databaseHelper);
        List<Material> materials = materialDao.getMaterials();
        for (Material material: materials) {
            System.out.println(material.toString());
        }
        System.out.println();

        TraitDao traitDao = new TraitDaoImpl(databaseHelper);
        List<Trait> traits = traitDao.getTraitFor(TraitType.ARMOR);
        for (Trait trait : traits) {
            System.out.println(trait.toString());
        }

        databaseHelper.close();
    }
}
