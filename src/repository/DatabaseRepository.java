package repository;

import db.DatabaseHelper;
import db.dao.MaterialDaoImpl;
import db.dao.TraitDaoImpl;
import db.dao.interfaces.MaterialDao;
import db.dao.interfaces.TraitDao;
import entitites.*;
import util.QualityResourceCollector;

import java.util.List;

public class DatabaseRepository {

    private final MaterialDao materialDao;
    private final TraitDao traitDao;
    private final QualityResourceCollector qualityResourceCollector;

    public DatabaseRepository(DatabaseHelper databaseHelper){
        materialDao = new MaterialDaoImpl(databaseHelper);
        traitDao = new TraitDaoImpl(databaseHelper);
        qualityResourceCollector = new QualityResourceCollector(materialDao);
    }

    public Material getMaterialFor(MaterialType type){
        return materialDao.getMaterialFor(type);
    }

    public List<Trait> getTraitFor(TraitType traitType){
        return traitDao.getTraitsFor(traitType);
    }

    public List<CraftResource> getUpgradeResourcesFor(QualityType qualityType, Workbench workbench) {
        return qualityResourceCollector.getResourcesFor(qualityType, workbench);
    }
}
