package gui.main;


import entitites.CraftResource;
import entitites.Item;

import javax.swing.*;

public class MainScreenPresenter {
    //Models
    private final DefaultListModel<CraftResource> craftResourcesModel;
    private final DefaultListModel<Item> itemsModel;
    //Variables
    private int totalPriceCounter;

    public MainScreenPresenter() {
        craftResourcesModel = new DefaultListModel<>();
        itemsModel = new DefaultListModel<>();
        totalPriceCounter = 0;

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
}

