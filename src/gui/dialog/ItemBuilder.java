package gui.dialog;

import entities.*;
import entities.armor.Armor;
import entities.armor.ArmorSlot;
import entities.armor.ArmorType;
import entities.jewelries.Jewelry;
import entities.jewelries.JewelryType;
import entities.weapon.Weapon;
import entities.weapon.WeaponType;

import java.util.List;

public class ItemBuilder {

    private final NewItemDialogPresenter presenter;

    public ItemBuilder(NewItemDialogPresenter presenter) {
        this.presenter = presenter;
    }

    public Item createItem() {
        TraitType selectedItemType = (TraitType) presenter.getComboItemTypeModel().getSelectedItem();
        switch (selectedItemType) {
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
        ArmorType armorType = getArmorType();
        ArmorSlot armorSlot = (ArmorSlot) getItemSlot();
        Trait armorTrait = getItemTrait();
        Material armorBaseMaterial = getBaseMaterial(armorTrait, armorSlot);
        Workbench armorWorkbench = getWorkbench(armorType);
        QualityType armorQuality = getQualityType();
        List<CraftResource> armorQualityMaterials = getQualityMaterials(armorQuality, armorWorkbench);
        Armor newArmor = new Armor(
                armorType,
                armorSlot,
                armorTrait,
                armorBaseMaterial,
                armorWorkbench
        );
        newArmor.setQuality(
                armorQuality,
                armorQualityMaterials
        );
        return newArmor;
    }

    private ArmorType getArmorType() {
        return (ArmorType) presenter.getComboArmorTypeModel().getSelectedItem();
    }

    private Object getItemSlot() {
        return presenter.getComboItemSlotModel().getSelectedItem();
    }

    private Trait getItemTrait() {
        return (Trait) presenter.getComboTraitModel().getSelectedItem();
    }

    private Material getBaseMaterial(Trait trait, Object itemSlot) {
        MaterialType materialType = null;
        TraitType traitType = trait.getTraitType();
        switch (traitType) {
            case WEAPON:
                materialType = MaterialType.getBaseMaterialTypeFor((WeaponType) itemSlot);
                break;
            case ARMOR:
                materialType = MaterialType.getBaseMaterialTypeFor(getArmorType(), (ArmorSlot) itemSlot);
                break;
            case JEWELRY:
                materialType = MaterialType.getBaseMaterialTypeFor((JewelryType) itemSlot);
        }
        return presenter.getMaterialFor(materialType);
    }

    private Workbench getWorkbench(WeaponType weaponType) {
        return weaponType.getWorkbench();
    }

    private Workbench getWorkbench(ArmorType armorType) {
        return armorType.getWorkbench();
    }

    private Workbench getWorkbench(JewelryType jewelryType) {
        return jewelryType.getWorkbench();
    }

    private QualityType getQualityType() {
        return (QualityType) presenter.getComboQualityModel().getSelectedItem();
    }

    private List<CraftResource> getQualityMaterials(QualityType qualityType, Workbench workbench) {
        return presenter.getQualityUpgradeMaterials(qualityType, workbench);
    }

    private Weapon createNewWeapon() {
        WeaponType weaponType = (WeaponType) getItemSlot();
        Trait weaponTrait = getItemTrait();
        Material weaponBaseMaterial = getBaseMaterial(weaponTrait, weaponType);
        Workbench weaponWorkbench = getWorkbench(weaponType);
        QualityType weaponQuality = getQualityType();
        List<CraftResource> weaponQualityMaterials = getQualityMaterials(weaponQuality, weaponWorkbench);

        Weapon newWeapon = new Weapon(
                weaponType,
                weaponTrait,
                weaponBaseMaterial,
                weaponWorkbench
        );
        newWeapon.setQuality(
                weaponQuality,
                weaponQualityMaterials
        );
        return newWeapon;
    }

    private Jewelry createNewJewelry() {
        JewelryType jewelryType = (JewelryType) getItemSlot();
        Trait jewelryTrait = getItemTrait();
        Material jewelryBaseMaterial = getBaseMaterial(jewelryTrait, jewelryType);
        Workbench jewelryWorkbench = getWorkbench(jewelryType);
        QualityType jewelryQuality = getQualityType();
        List<CraftResource> jewelryQualityMaterials = getQualityMaterials(jewelryQuality, jewelryWorkbench);

        Jewelry newJewelry = new Jewelry(
                jewelryType,
                jewelryTrait,
                jewelryBaseMaterial,
                jewelryWorkbench
        );
        newJewelry.setQuality(
                jewelryQuality,
                jewelryQualityMaterials
        );
        return newJewelry;
    }
}
