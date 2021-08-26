package gui.dialog;

import entities.*;
import entities.armor.ArmorSlot;
import entities.armor.ArmorType;
import entities.jewelries.JewelryType;
import entities.weapon.WeaponType;
import util.DatabaseRepository;

import javax.swing.*;
import java.util.List;

public class NewItemDialogPresenter {

    private final DatabaseRepository databaseRepository;
    private final ItemBuilder itemBuilder;

    private final DefaultComboBoxModel<TraitType> comboItemTypeModel = new DefaultComboBoxModel<>(TraitType.values());
    private final DefaultComboBoxModel<Object> comboItemSlotModel = new DefaultComboBoxModel<>();
    private final DefaultComboBoxModel<ArmorType> comboArmorTypeModel = new DefaultComboBoxModel<>(ArmorType.values());
    private final DefaultComboBoxModel<Trait> comboTraitModel = new DefaultComboBoxModel<>();
    private final DefaultComboBoxModel<QualityType> comboQualityModel = new DefaultComboBoxModel<>(QualityType.values());


    public NewItemDialogPresenter(final DatabaseRepository databaseRepository) {
        this.databaseRepository = databaseRepository;
        onItemTypeChanged();
        itemBuilder = new ItemBuilder(this);

    }
    //Item creation tools

    public Item createItem() {
        return itemBuilder.createItem();
    }

    //Combo boxes models updater

    public void onItemTypeChanged() {
        TraitType selectedItemType = (TraitType) comboItemTypeModel.getSelectedItem();
        setComboItemSlotModel(selectedItemType);
        setComboTraitModel(selectedItemType);
    }

    public boolean shouldArmorSlotBeVisible() {
        Object armorSlot = comboItemSlotModel.getSelectedItem();
        return comboItemTypeModel.getSelectedItem() == TraitType.ARMOR && armorSlot != ArmorSlot.SHIELD;
    }

    //Getters

    public ComboBoxModel<TraitType> getComboItemTypeModel() {
        return comboItemTypeModel;
    }

    public DefaultComboBoxModel<Object> getComboItemSlotModel() {
        return comboItemSlotModel;
    }

    private void setComboItemSlotModel(TraitType itemType) {
        Object[] valuesToAdd = null;
        switch (itemType) {
            case ARMOR:
                valuesToAdd = ArmorSlot.values();
                break;
            case WEAPON:
                valuesToAdd = WeaponType.values();
                break;
            case JEWELRY:
                valuesToAdd = JewelryType.values();
        }
        comboItemSlotModel.removeAllElements();
        if (valuesToAdd != null) {
            for (Object value :
                    valuesToAdd) {
                comboItemSlotModel.addElement(value);
            }
        }
    }

    public ComboBoxModel<ArmorType> getComboArmorTypeModel() {
        return comboArmorTypeModel;
    }

    public ComboBoxModel<Trait> getComboTraitModel() {
        return comboTraitModel;
    }

    private void setComboTraitModel(TraitType traitType) {
        comboTraitModel.removeAllElements();
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

    public Material getMaterialFor(MaterialType materialType) {
        return databaseRepository.getMaterialFor(materialType);
    }

    public List<CraftResource> getQualityUpgradeMaterials(QualityType qualityType, Workbench workbench) {
        return databaseRepository.getQualityResourcesFor(qualityType, workbench);
    }
}