package gui.main;


import entities.CraftResource;
import entities.Item;

import javax.swing.*;

public class MainScreenPresenter {

    private final DefaultListModel<CraftResource> craftResourcesModel;
    private final DefaultListModel<Item> itemsModel;
    private final SpinnerNumberModel spinnerModel;

    private int totalPriceCounter;

    public MainScreenPresenter() {
        craftResourcesModel = new DefaultListModel<>();
        itemsModel = new DefaultListModel<>();
        spinnerModel = createSpinnerModel();
        totalPriceCounter = 0;
    }

    private SpinnerNumberModel createSpinnerModel() {
        double value = 15;
        double min = 0;
        double max = 10000;
        double step = 1;
        return new SpinnerNumberModel(value, min, max, step);
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

    public void recalculateTotalPrice() {
        totalPriceCounter = 0;
        if (itemsModel.size() > 0) {
            for (int i = 0; i < itemsModel.size(); i++) {
                totalPriceCounter += itemsModel.get(i).getPrice();
            }
        }
    }

    public DefaultListModel<Item> getItemsModel() {
        return itemsModel;
    }

    public SpinnerNumberModel getSpinnerModel() {
        return spinnerModel;
    }

    public Integer getTotalPriceCounter() {
        return getTotalPriceWithSurplusValue();
    }

    private int getTotalPriceWithSurplusValue() {
        double percentToCoefficient = 0.01;
        double surplusValuePercentage = (Double) spinnerModel.getNumber() * percentToCoefficient;
        double result = totalPriceCounter;
        result += result * surplusValuePercentage;
        return (int) Math.round(result);
    }
}

