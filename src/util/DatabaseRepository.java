package util;

import db.DatabaseHelper;
import db.dao.MaterialDaoImpl;
import db.dao.TraitDaoImpl;
import db.dao.interfaces.MaterialDao;
import db.dao.interfaces.TraitDao;
import entities.*;

import java.util.List;

public class DatabaseRepository {

    private final MaterialDao materialDao;
    private final TraitDao traitDao;
    private final QualityResourceCollector qualityResourceCollector;
    private final DatabaseHelper database;


    public DatabaseRepository(DatabaseHelper databaseInstance) {
        database = databaseInstance;
        materialDao = new MaterialDaoImpl(database);
        traitDao = new TraitDaoImpl(database);
        qualityResourceCollector = new QualityResourceCollector(materialDao);
    }

    public void databaseDisconnect() {
        database.close();
    }

    public Material getMaterialFor(MaterialType materialType) {
        return materialDao.getMaterialFor(materialType);
    }

    public List<Material> getAllMaterials() {
        return materialDao.getAllMaterials();
    }

    public List<Trait> getTraitFor(TraitType traitType) {
        return traitDao.getTraitFor(traitType);
    }

    public List<CraftResource> getQualityResourcesFor(QualityType qualityType, Workbench workbench) {
        return qualityResourceCollector.getResourcesFor(qualityType, workbench);
    }

    public void updateMaterial(Material updatedMaterial) {
        materialDao.setMaterial(updatedMaterial);
    }


}
