package db.dao.interfaces;

import db.DatabaseHelper;
import db.parsers.Parser;
import entitites.Material;

import java.util.List;

public interface MaterialDao {
    List<Material> getMaterials();
}
