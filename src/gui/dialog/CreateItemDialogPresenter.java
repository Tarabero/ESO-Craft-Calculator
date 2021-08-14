package gui.dialog;

import db.entities.Material;
import db.entities.MaterialType;
import db.entities.Trait;
import db.entities.TraitType;
import item.*;
import item.armor.ArmorSlot;
import item.armor.ArmorType;
import item.jewelries.JewelryType;
import item.weapon.WeaponType;
import repository.DatabaseRepository;

import javax.swing.*;
import java.util.List;

public class CreateItemDialogPresenter {

    private DatabaseRepository repository;

    private final DefaultComboBoxModel<TraitType> itemTypeModel = new DefaultComboBoxModel<>(TraitType.values());
    private final DefaultComboBoxModel<Trait> itemTraitModel = new DefaultComboBoxModel<>();
    private final DefaultComboBoxModel<QualityType> itemQualityModel = new DefaultComboBoxModel<>(QualityType.values());
    private final DefaultComboBoxModel<Object> itemSlotModel = new DefaultComboBoxModel<>();

    private final DefaultComboBoxModel<ArmorType> armorTypeModel = new DefaultComboBoxModel<>(ArmorType.values());

    public CreateItemDialogPresenter(DatabaseRepository databaseRepository) {
        repository = databaseRepository;
        onItemTypeChanged();
    }

    public void onItemTypeChanged() {
        updateTraits();
        updateItemSlot();
    }

    private void updateTraits() {
        if (getSelectedTraitType() != null) {
            itemTraitModel.removeAllElements();
            List<Trait> traits = repository.getTraitFor(getSelectedTraitType());
            for (Trait trait :
                    traits) {
                itemTraitModel.addElement(trait);
            }
        }
    }

    private TraitType getSelectedTraitType() {
        return (TraitType) itemTypeModel.getSelectedItem();
    }

    private void updateItemSlot() {
        if (getSelectedTraitType() == null) {
            return;
        }
        itemSlotModel.removeAllElements();
        Object[] slots = null;
        switch (getSelectedTraitType()) {
            case ARMOR:
                slots = ArmorSlot.values();
                break;
            case WEAPON:
                slots = WeaponType.values();
                break;
            case JEWELRY:
                slots = JewelryType.values();
                break;
        }
        if (slots != null) {
            for (Object slot : slots) {
                itemSlotModel.addElement(slot);
            }
        }
    }

    public Item createItem() {
        if (getSelectedTraitType() == null) {
            return null;
        }
        ItemBuilder itemBuilder = new ItemBuilder();
        return itemBuilder.setTraitType(getSelectedTraitType())
                .setTrait(getSelectedTrait())
                .setQuality(getSelectedQualityType(), getQualityResourceList())
                .setBaseMaterial(getBaseMaterial())
                .setSlot(getSelectedSlot())
                .setArmorType(getSelectedArmorType())
                .build();
    }

    private Trait getSelectedTrait() {
        return (Trait) itemTraitModel.getSelectedItem();
    }

    private QualityType getSelectedQualityType() {
        return (QualityType) itemQualityModel.getSelectedItem();
    }

    private Object getSelectedSlot() {
        return itemSlotModel.getSelectedItem();
    }

    private List<CraftResource> getQualityResourceList() {
        return repository.getUpgradeResourcesFor(getSelectedQualityType(), getWorkbench());
    }

    private Workbench getWorkbench() {
        Object selectedSlot = getSelectedSlot();
        if (selectedSlot instanceof ArmorSlot) {
            return ((ArmorSlot) selectedSlot).getWorkbench(getSelectedArmorType());
        }
        if (selectedSlot instanceof WeaponType) {
            return ((WeaponType) selectedSlot).getWorkbench();
        }
        if (selectedSlot instanceof JewelryType) {
            return ((JewelryType) selectedSlot).getWorkbench();
        }
        return null;
    }

    private Material getBaseMaterial() {
        return repository.getMaterialFor(getBaseMaterialType());
    }

    private MaterialType getBaseMaterialType() {
        Object selectedSlot = getSelectedSlot();
        if (selectedSlot instanceof ArmorSlot) {
            return ((ArmorSlot) selectedSlot).getBaseMaterialType(getSelectedArmorType());
        }
        if (selectedSlot instanceof WeaponType) {
            return ((WeaponType) selectedSlot).getBaseMaterialType();
        }
        if (selectedSlot instanceof JewelryType) {
            return ((JewelryType) selectedSlot).getBaseMaterialType();
        }
        return null;
    }

    private ArmorType getSelectedArmorType() {
        return (ArmorType) armorTypeModel.getSelectedItem();
    }

    public boolean shouldShowArmorTypes() {
        Object slot = getSelectedSlot();
        return getSelectedTraitType() == TraitType.ARMOR && slot != ArmorSlot.SHIELD;
    }

    public DefaultComboBoxModel<TraitType> getItemTypeModel() {
        return itemTypeModel;
    }

    public DefaultComboBoxModel<Trait> getItemTraitModel() {
        return itemTraitModel;
    }

    public DefaultComboBoxModel<QualityType> getItemQualityModel() {
        return itemQualityModel;
    }

    public DefaultComboBoxModel<Object> getItemSlotModel() {
        return itemSlotModel;
    }

    public DefaultComboBoxModel<ArmorType> getArmorTypeModel() {
        return armorTypeModel;
    }
}
