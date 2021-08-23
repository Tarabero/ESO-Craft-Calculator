package gui.main;

import item.CraftResource;
import item.Item;

import javax.swing.*;
import java.util.Enumeration;
import java.util.List;

public class MainPresenter {

    private final DefaultListModel<Item> itemsModel = new DefaultListModel<>();
    private final DefaultListModel<CraftResource> materialsModel = new DefaultListModel<>();
    private int totalPrice = 0;

    public void onNewItemCreated(Item item) {
        itemsModel.addElement(item);
        List<CraftResource> itemResources = item.getAllCraftingResources();
        addToMaterialsTotal(itemResources);
        updateTotalPrice();
    }

    private void addToMaterialsTotal(List<CraftResource> itemResources) {
        for (CraftResource craftResource :
                itemResources) {
            if (materialsModel.contains(craftResource)) {
                int savedResourceIndex = materialsModel.indexOf(craftResource);
                CraftResource storedCraftResource = materialsModel.get(savedResourceIndex);
                int storedQuantity = storedCraftResource.getQuantity();
                storedCraftResource.setQuantity(storedQuantity + craftResource.getQuantity());
                materialsModel.set(savedResourceIndex, storedCraftResource);
            } else {
                materialsModel.addElement(new CraftResource(craftResource.getMaterial(), craftResource.getQuantity()));
            }
        }
    }

    public void removeItem(int position) {
        Item removedItem = itemsModel.remove(position);
        List<CraftResource> itemResources = removedItem.getAllCraftingResources();
        removeFromMaterialsTotal(itemResources);
        updateTotalPrice();
    }

    private void removeFromMaterialsTotal(List<CraftResource> itemResources) {
        for (CraftResource craftResource :
                itemResources) {
            if (materialsModel.contains(craftResource)) {
                int savedResourceIndex = materialsModel.indexOf(craftResource);
                CraftResource storedCraftResource = materialsModel.get(savedResourceIndex);
                int storedQuantity = storedCraftResource.getQuantity();
                int newQuantity = storedQuantity - craftResource.getQuantity();
                if (newQuantity <= 0) {
                    materialsModel.remove(savedResourceIndex);
                    continue;
                }
                storedCraftResource.setQuantity(newQuantity);
                materialsModel.set(savedResourceIndex, storedCraftResource);
            }
        }
    }

    public String getTotalPrice() {
        return "Total: " + totalPrice;
    }

    private void updateTotalPrice() {
        totalPrice = 0;
        Enumeration<CraftResource> craftResourceEnumeration = materialsModel.elements();
        while (craftResourceEnumeration.hasMoreElements()) {
            CraftResource craftResource = craftResourceEnumeration.nextElement();
            totalPrice += craftResource.getTotalPrice();
        }
    }

    public DefaultListModel<Item> getItemsModel() {
        return itemsModel;
    }

    public DefaultListModel<CraftResource> getMaterialsModel() {
        return materialsModel;
    }
}
