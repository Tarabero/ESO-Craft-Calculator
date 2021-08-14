import db.DatabaseHelper;
import db.dao.MaterialDaoImpl;
import db.dao.TraitDaoImpl;
import db.dao.interfaces.MaterialDao;
import db.dao.interfaces.TraitDao;
import entitites.*;
import entitites.armor.ArmorSlot;
import entitites.armor.ArmorType;
import entitites.jewelries.JewelryType;
import entitites.weapon.WeaponType;
import gui.MainScreen;
import gui.MainScreenPresenter;
import util.QualityResourceCollector;

import java.util.List;

public class Main {
    // VARs

    public static void main (String[] args) {
        MainScreen mainScreenWindow = new MainScreen(new MainScreenPresenter());
    }

    //CRAFT RESOURCE GETTERS (I DON'T KNOW WHY THEY ARE HERE T_T)
    public static List<CraftResource> GetResourcesForArmor(ArmorType armorType, ArmorSlot armorSlot, QualityType qualityType, Trait trait) {
        return null;
    }
    public static List<CraftResource> GetResourcesForWeapon(WeaponType weaponType, QualityType qualityType, Trait trait) {
        return null;
    }
    public static List<CraftResource> GetResourcesForJewelry(JewelryType jewelryType, QualityType qualityType, Trait trait) {
        return null;
    }

    // DATABASE CONNECT, GET AND DISCONNECT METHODS
    private static DatabaseHelper ConnectAndGetDatabase() {
        DatabaseHelper databaseHelper = DatabaseHelper.getInstance();
        databaseHelper.connect();
        return databaseHelper;
    }
    private static void DisconnectDatabase(DatabaseHelper databaseHelper) {
        databaseHelper.close();
    }
    // DAO GETTER FROM DATABASE (aka DB)
    public static QualityResourceCollector CollectQualityResourcesFromDB() {
        MaterialDao materialDao = GetMaterialDaoFromDB();
        return new QualityResourceCollector(materialDao);
    }
    public static MaterialDao GetMaterialDaoFromDB() {
        DatabaseHelper databaseHelper = ConnectAndGetDatabase();
        MaterialDao materialDao = new MaterialDaoImpl(databaseHelper);
        DisconnectDatabase(databaseHelper);
        return materialDao;
    }
    public static TraitDao GetTraitDaoFromDB() {
        DatabaseHelper databaseHelper = ConnectAndGetDatabase();
        TraitDao traitDao = new TraitDaoImpl(databaseHelper);
        DisconnectDatabase(databaseHelper);
        return traitDao;
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
