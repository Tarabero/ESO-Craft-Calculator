package gui;

import db.DatabaseHelper;
import entitites.*;
import entitites.armor.Armor;
import entitites.armor.ArmorSlot;
import entitites.armor.ArmorType;
import repository.DatabaseRepository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class MainPresenter {

    private final DatabaseRepository databaseRepository;

    public MainPresenter(DatabaseHelper databaseHelper){
        databaseRepository = new DatabaseRepository(databaseHelper);
    }

    public List<Item> getItemsList(){
        ArrayList<Item> items = new ArrayList<>();
        Armor armor = new Armor(
                ArmorType.LIGHT,
                ArmorSlot.HELMET,
                databaseRepository.getTraitFor(TraitType.ARMOR).get(0),
                databaseRepository.getMaterialFor(MaterialType.BASE_CLOTH),
                Workbench.CLOTHING
                );
        armor.setQuality(QualityType.COMMON,
                databaseRepository.getResourcesFor(QualityType.COMMON, Workbench.CLOTHING));
        QualityType type = null;
        armor.setQuality(type, null);

        Armor armor1 = new Armor(
                ArmorType.MEDIUM,
                ArmorSlot.HELMET,
                databaseRepository.getTraitFor(TraitType.ARMOR).get(0),
                databaseRepository.getMaterialFor(MaterialType.BASE_LEATHER),
                Workbench.CLOTHING
        );
        armor1.setQuality(QualityType.COMMON,
                databaseRepository.getResourcesFor(QualityType.COMMON, Workbench.CLOTHING));

        Armor armor2 = new Armor(
                ArmorType.HEAVY,
                ArmorSlot.HELMET,
                databaseRepository.getTraitFor(TraitType.ARMOR).get(0),
                databaseRepository.getMaterialFor(MaterialType.BASE_METAL),
                Workbench.SMITHING
        );
        armor2.setQuality(QualityType.COMMON,
                databaseRepository.getResourcesFor(QualityType.COMMON, Workbench.SMITHING));

        items.addAll(Arrays.asList(armor, armor1, armor2));

        return items;
    }
}
