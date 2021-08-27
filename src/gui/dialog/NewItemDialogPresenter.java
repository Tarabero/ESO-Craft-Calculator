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

    private final DefaultComboBoxModel<TraitType> comboItemTypeModel = new DefaultComboBoxModel<>(TraitType.values());
    private final DefaultComboBoxModel<Object> comboItemSlotModel = new DefaultComboBoxModel<>();
    private final DefaultComboBoxModel<ArmorType> comboArmorTypeModel = new DefaultComboBoxModel<>(ArmorType.values());
    private final DefaultComboBoxModel<Trait> comboTraitModel = new DefaultComboBoxModel<>();
    private final DefaultComboBoxModel<QualityType> comboQualityModel = new DefaultComboBoxModel<>(QualityType.values());


    public NewItemDialogPresenter(final DatabaseRepository databaseRepository) {
        this.databaseRepository = databaseRepository;
        onItemTypeChanged();

    }

    //Item creation call

    public Item createItem() {
        ItemBuilder itemBuilder = new ItemBuilder();
        return itemBuilder
                .setItemType(getItemType())
                .setItemSlot(getItemSlot())
                .setArmorType(getArmorType())
                .setItemTrait(getItemTrait())
                .setItemBaseMaterial(getItemBaseMaterial())
                .setItemQuality(getItemQualityType(), getItemQualityResources())
                .buildItem();
    }

    // Item data gathering

    private TraitType getItemType() {
        return (TraitType) comboItemTypeModel.getSelectedItem();
    }

    private Object getItemSlot() {
        return comboItemSlotModel.getSelectedItem();
    }

    private ArmorType getArmorType() {
        return (ArmorType) comboArmorTypeModel.getSelectedItem();
    }

    private Trait getItemTrait() {
        return (Trait) comboTraitModel.getSelectedItem();
    }

    private Material getItemBaseMaterial() {
        return databaseRepository.getMaterialFor(getItemBaseMaterialType());
    }

    private MaterialType getItemBaseMaterialType() {
        TraitType selectedItemType = getItemType();
        switch (selectedItemType) {
            case WEAPON:
                return MaterialType.getBaseMaterialTypeFor((WeaponType) getItemSlot());
            case ARMOR:
                return MaterialType.getBaseMaterialTypeFor(getArmorType(), (ArmorSlot) getItemSlot());
            case JEWELRY:
                return MaterialType.getBaseMaterialTypeFor((JewelryType) getItemSlot());
        }
        return null;
    }

    private QualityType getItemQualityType() {
        return (QualityType) comboQualityModel.getSelectedItem();
    }

    private List<CraftResource> getItemQualityResources() {
        return databaseRepository.getQualityResourcesFor(getItemQualityType(), getItemWorkbench());
    }

    private Workbench getItemWorkbench() {
        Object selectedItemSlot = getItemSlot();
        if (selectedItemSlot instanceof WeaponType) {
            return ((WeaponType) selectedItemSlot).getWorkbench();
        }
        if (selectedItemSlot instanceof ArmorSlot) {
            return ((ArmorSlot) selectedItemSlot).getWorkbench(getArmorType());
        }
        if (selectedItemSlot instanceof JewelryType) {
            return ((JewelryType) selectedItemSlot).getWorkbench();
        }
        return null;
    }

    //Combo boxes models updater

    public void onItemTypeChanged() {
        TraitType selectedItemType = (TraitType) comboItemTypeModel.getSelectedItem();
        updateComboItemSlotModel(selectedItemType);
        updateComboTraitModel(selectedItemType);
    }

    private void updateComboItemSlotModel(TraitType itemType) {
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

    private void updateComboTraitModel(TraitType traitType) {
        comboTraitModel.removeAllElements();
        for (Trait trait :
                databaseRepository.getTraitFor(traitType)) {
            comboTraitModel.addElement(trait);
        }
    }

    public boolean shouldArmorTypeBeVisible() {
        Object armorSlot = comboItemSlotModel.getSelectedItem();
        return comboItemTypeModel.getSelectedItem() == TraitType.ARMOR && armorSlot != ArmorSlot.SHIELD;
    }

    //Models getters

    public ComboBoxModel<TraitType> getComboItemTypeModel() {
        return comboItemTypeModel;
    }

    public DefaultComboBoxModel<Object> getComboItemSlotModel() {
        return comboItemSlotModel;
    }

    public ComboBoxModel<ArmorType> getComboArmorTypeModel() {
        return comboArmorTypeModel;
    }

    public ComboBoxModel<Trait> getComboTraitModel() {
        return comboTraitModel;
    }

    public ComboBoxModel<QualityType> getComboQualityModel() {
        return comboQualityModel;
    }
}