package db.dao.interfaces;

import entitites.Trait;
import entitites.TraitType;

import java.util.List;

public interface TraitDao {
    List<Trait> getTraitFor(TraitType traitType);
}
