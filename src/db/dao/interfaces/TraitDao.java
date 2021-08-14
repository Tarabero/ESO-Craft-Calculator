package db.dao.interfaces;

import db.entities.Trait;
import db.entities.TraitType;

import java.util.List;

public interface TraitDao {
    List<Trait> getTraitsFor(TraitType traitType);
}
