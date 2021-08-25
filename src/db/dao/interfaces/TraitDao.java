package db.dao.interfaces;

import entities.Trait;
import entities.TraitType;

import java.util.List;

public interface TraitDao {
    List<Trait> getTraitFor(TraitType traitType);
}
