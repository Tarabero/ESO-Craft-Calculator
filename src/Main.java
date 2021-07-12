import db.DatabaseHelper;
import db.dao.MaterialDaoImpl;
import db.dao.TraitDaoImpl;
import db.dao.interfaces.MaterialDao;
import db.dao.interfaces.TraitDao;
import entitites.Material;
import entitites.MaterialType;
import entitites.Trait;
import entitites.TraitType;

import java.util.List;

public class Main {

    public static void main(String[] args) {
        DatabaseHelper databaseHelper = DatabaseHelper.getInstance();
        databaseHelper.connect();
        MaterialDao materialDao = new MaterialDaoImpl(databaseHelper);

        TraitDao traitDao = new TraitDaoImpl(databaseHelper);

        databaseHelper.close();
    }

    // Test methods

    private void printAllTraits(TraitDao traitDao){
        List<Trait> traits = traitDao.getTraitFor(TraitType.ARMOR);
        for (Trait trait : traits) {
            System.out.println(trait.toString());
        }
    }

    private void printAllMaterials(MaterialDao materialDao){
        List<Material> materials = materialDao.getMaterials();
        for (Material material: materials) {
            System.out.println(material.toString());
        }
        System.out.println();
    }

    private void printSingleMaterial(MaterialDao materialDao, MaterialType type){
        Material material = materialDao.getMaterialFor(type);
        System.out.println(material.getName());
        System.out.println(material.getMaterialType().toString());
        System.out.println(material.getPrice());
    }
}
