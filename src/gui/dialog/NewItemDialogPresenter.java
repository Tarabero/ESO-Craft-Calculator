package gui.dialog;

import entitites.*;
import entitites.armor.Armor;
import entitites.armor.ArmorSlot;
import entitites.armor.ArmorType;
import entitites.jewelries.Jewelry;
import entitites.jewelries.JewelryType;
import entitites.weapon.Weapon;
import entitites.weapon.WeaponType;
import util.DatabaseRepository;

import javax.swing.*;

public class NewItemDialogPresenter {

    private final DatabaseRepository databaseRepository;

    private final DefaultComboBoxModel<TraitType> comboItemTypeModel = new DefaultComboBoxModel<>(TraitType.values());
    private final DefaultComboBoxModel<Object> comboItemSlotModel = new DefaultComboBoxModel<>();
    private final DefaultComboBoxModel<ArmorType> comboArmorTypeModel = new DefaultComboBoxModel<>(ArmorType.values());
    private final DefaultComboBoxModel<Trait> comboTraitModel = new DefaultComboBoxModel<>();
    private final DefaultComboBoxModel<QualityType> comboQualityModel = new DefaultComboBoxModel<>(QualityType.values());


    public NewItemDialogPresenter(final DatabaseRepository databaseRepository) {
        this.databaseRepository = databaseRepository;
    }

    //Item creation tools

    public Item createItem() {
        TraitType selected = (TraitType) comboItemTypeModel.getSelectedItem();
        switch (selected) {
            case ARMOR:
                return createNewArmor();
            case WEAPON:
                return createNewWeapon();
            case JEWELRY:
                return createNewJewelry();
        }
        return null;
    }

    private Armor createNewArmor() {
        ArmorSlot armorSlot = (ArmorSlot) comboItemSlotModel.getSelectedItem();
        Trait armorTrait = (Trait) comboTraitModel.getSelectedItem();
        ArmorType armorType = (ArmorType) comboArmorTypeModel.getSelectedItem();
        Workbench workbench = armorType.getWorkbench();
        Material itemBaseMaterial = databaseRepository.getMaterialFor(MaterialType.getBaseMaterialTypeFor(armorType, armorSlot));
        QualityType qualityType = (QualityType) comboQualityModel.getSelectedItem();

        Armor newArmor = new Armor(armorType, armorSlot, armorTrait, itemBaseMaterial, workbench);
        newArmor.setQuality(qualityType, databaseRepository.getQualityResourcesFor(qualityType, workbench));
        return newArmor;
    }

    private Weapon createNewWeapon() {
        WeaponType weaponType = (WeaponType) comboItemSlotModel.getSelectedItem();
        Trait weaponTrait = (Trait) comboTraitModel.getSelectedItem();
        Workbench workbench = weaponType.getWorkbench();
        Material itemBaseMaterial = databaseRepository.getMaterialFor(MaterialType.getBaseMaterialTypeFor(weaponType));
        QualityType qualityType = (QualityType) comboQualityModel.getSelectedItem();

        Weapon newWeapon = new Weapon(weaponType, weaponTrait, itemBaseMaterial, workbench);
        newWeapon.setQuality(qualityType, databaseRepository.getQualityResourcesFor(qualityType, workbench));
        return newWeapon;
    }

    private Jewelry createNewJewelry() {
        JewelryType jewelryType = (JewelryType) comboItemSlotModel.getSelectedItem();
        Trait weaponTrait = (Trait) comboTraitModel.getSelectedItem();
        Workbench workbench = jewelryType.getWorkbench();
        Material itemBaseMaterial = databaseRepository.getMaterialFor(MaterialType.getBaseMaterialTypeFor(jewelryType));
        QualityType qualityType = (QualityType) comboQualityModel.getSelectedItem();

        Jewelry newJewelry = new Jewelry(jewelryType, weaponTrait, itemBaseMaterial, workbench);
        newJewelry.setQuality(qualityType, databaseRepository.getQualityResourcesFor(qualityType, workbench));
        return newJewelry;
    }

    //Combo boxes models updater

    public void updateComboBoxesModels() {
        TraitType selectedItemType = (TraitType) comboItemTypeModel.getSelectedItem();
        setComboItemSlotModel(selectedItemType);
        setComboTraitModel(selectedItemType);
    }

    public boolean armorSlotMustBeVisible() {
        Object armorSlot = comboItemSlotModel.getSelectedItem();
        return comboItemTypeModel.getSelectedItem() == TraitType.ARMOR && armorSlot != ArmorSlot.SHIELD;
    }

    //Getters

    public ComboBoxModel<TraitType> getComboItemTypeModel() {
        return comboItemTypeModel;
    }

    public DefaultComboBoxModel<Object> getComboItemSlotModel() {
        TraitType selectedItemType = (TraitType) comboItemTypeModel.getSelectedItem();
        setComboItemSlotModel(selectedItemType);
        return comboItemSlotModel;
    }

    private void setComboItemSlotModel(TraitType itemType) {
        comboItemSlotModel.removeAllElements();
        switch (itemType) {
            case ARMOR:
                for (ArmorSlot itemSlot :
                        ArmorSlot.values()) {
                    comboItemSlotModel.addElement(itemSlot);
                }
                break;
            case WEAPON:
                for (WeaponType itemSlot :
                        WeaponType.values()) {
                    comboItemSlotModel.addElement(itemSlot);
                }
                break;
            case JEWELRY:
                for (JewelryType itemSlot :
                        JewelryType.values()) {
                    comboItemSlotModel.addElement(itemSlot);
                }
                break;
        }
    }

    public ComboBoxModel<ArmorType> getComboArmorTypeModel() {
        return comboArmorTypeModel;
    }

    public ComboBoxModel<Trait> getComboTraitModel() {
        TraitType selectedItemType = (TraitType) comboItemTypeModel.getSelectedItem();
        setComboTraitModel(selectedItemType);
        return comboTraitModel;
    }

    private void setComboTraitModel(TraitType traitType) {
        if (comboTraitModel.getSize() > 0) {
            comboTraitModel.removeAllElements();
            addTraitsToComboTraitModel(traitType);
        }
        addTraitsToComboTraitModel(traitType);
    }

    private void addTraitsToComboTraitModel(TraitType traitType) {
        for (Trait trait :
                databaseRepository.getTraitFor(traitType)) {
            comboTraitModel.addElement(trait);
        }
    }

    public ComboBoxModel<QualityType> getComboQualityModel() {
        return comboQualityModel;
    }
}