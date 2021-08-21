package gui;


import entitites.*;
import entitites.armor.Armor;
import entitites.armor.ArmorSlot;
import entitites.armor.ArmorType;
import util.DatabaseRepository;

import javax.swing.*;
import java.util.List;
import java.util.Random;

public class MainScreenPresenter {
    //Models
    private final DefaultListModel<CraftResource> craftResourcesModel;
    private final DefaultListModel<Item> itemsModel;
    //Database
    private final DatabaseRepository databaseRepository;
    //Variables
    private int totalPriceCounter;

    public MainScreenPresenter(DatabaseRepository databaseRepositoryInput) {
        craftResourcesModel = new DefaultListModel<>();
        itemsModel = new DefaultListModel<>();
        totalPriceCounter = 0;
        databaseRepository = databaseRepositoryInput;
    }

    public void eventsOnMainScreenExit() {
        databaseRepository.databaseDisconnect();
    }

    public void addItemToItemList(Item item) {
        increaseTotalPrice(item);
        addItemMaterialsToMaterialList(item);
        itemsModel.addElement(item);
    }

    private void increaseTotalPrice(Item item) {
        totalPriceCounter += item.getPrice();
    }

    private void addItemMaterialsToMaterialList(Item item) {
        for (CraftResource craftResourceFromItem : item.getAllCraftingResources()) {
            if (craftResourcesModel.contains(craftResourceFromItem)) {
                int storedResourceIndex = craftResourcesModel.indexOf(craftResourceFromItem);
                CraftResource storedCraftResource = craftResourcesModel.getElementAt(storedResourceIndex);
                int quantityUpdated = storedCraftResource.getQuantity() + craftResourceFromItem.getQuantity();

                storedCraftResource.setQuantity(quantityUpdated);
                craftResourcesModel.set(storedResourceIndex, storedCraftResource);
            } else {
                craftResourcesModel.addElement(new CraftResource(craftResourceFromItem.getMaterial(), craftResourceFromItem.getQuantity()));
            }
        }
    }

    public void removeItemFromItemList(int itemPosition) {
        if (itemsModel.size() > 0) {
            if (itemPosition == -1) {
                actionRemoveItem(itemsModel.lastElement());
            } else {
                actionRemoveItem(itemsModel.get(itemPosition));
            }
        }
    }

    private void actionRemoveItem(Item item) {
        decreaseTotalPrice(item);
        removeItemMaterialsFromMaterialList(item);
        itemsModel.removeElement(item);
    }

    private void decreaseTotalPrice(Item item) {
        totalPriceCounter -= item.getPrice();
    }

    private void removeItemMaterialsFromMaterialList(Item item) {
        for (CraftResource craftResourceFromItem : item.getAllCraftingResources()) {
            if (craftResourcesModel.contains(craftResourceFromItem)) {
                int storedResourceIndex = craftResourcesModel.indexOf(craftResourceFromItem);
                CraftResource storedCraftResource = craftResourcesModel.getElementAt(storedResourceIndex);
                int quantitiesSub = storedCraftResource.getQuantity() - craftResourceFromItem.getQuantity();
                if (quantitiesSub <= 0) {
                    craftResourcesModel.remove(storedResourceIndex);
                } else {
                    storedCraftResource.setQuantity(quantitiesSub);
                    craftResourcesModel.set(storedResourceIndex, storedCraftResource);
                }
            }
        }
    }

    public DefaultListModel<CraftResource> getCraftResourcesModel() {
        return craftResourcesModel;
    }

    public DefaultListModel<Item> getItemsModel() {
        return itemsModel;
    }

    public Integer getTotalPriceCounter() {
        return totalPriceCounter;
    }

    // Random item getter and it's methods (SHOULD BE REMOVED AFTER ADDING A PROPER ITEM CREATOR)

    public Item getRandomItem() {
        //Gathering Data from Database Repository
        List<Trait> traitsForItem = databaseRepository.getTraitFor(TraitType.ARMOR);
        List<Material> materialsForItem = databaseRepository.getAllMaterials();
        ArmorType itemType = getRandomArmorType();
        ArmorSlot itemSlot = ArmorSlot.CHEST;
        Trait itemTrait = traitsForItem.get(0);
        Material itemBaseMaterial = databaseRepository.getMaterialFor(MaterialType.BASE_CLOTH);
        Workbench itemWorkbench = Workbench.CLOTHING;
        QualityType itemQualityType = getRandomQuality();
        //Item (Armor) creation
        Armor item = new Armor(itemType, itemSlot, itemTrait, itemBaseMaterial, itemWorkbench);
        item.setQualityAndCreateName(itemQualityType, databaseRepository.getQualityResourcesFor(itemQualityType, itemWorkbench));
        return item;
    }

    private QualityType getRandomQuality() {
        Random random = new Random();
        int rnd = random.nextInt(5);
        switch (rnd) {
            case 0:
                return QualityType.COMMON;
            case 1:
                return QualityType.FINE;
            case 2:
                return QualityType.RARE;
            case 3:
                return QualityType.EPIC;
            case 4:
                return QualityType.LEGENDARY;
        }
        return QualityType.COMMON;
    }

    private ArmorType getRandomArmorType() {
        Random random = new Random();
        int rnd = random.nextInt(3);
        switch (rnd) {
            case 0:
                return ArmorType.LIGHT;
            case 1:
                return ArmorType.MEDIUM;
            case 2:
                return ArmorType.HEAVY;
        }
        return ArmorType.LIGHT;
    }

}

