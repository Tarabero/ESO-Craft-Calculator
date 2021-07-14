package util;

import db.dao.interfaces.MaterialDao;
import entitites.CraftResource;
import entitites.QualityType;
import entitites.Workbench;

import java.util.List;

public class QualityResourceCollector {

    public QualityResourceCollector(MaterialDao materialDao) {

    }

    public List<CraftResource> getResourcesFor(QualityType qualityType, Workbench workbench) {
        return null;
    }

}
