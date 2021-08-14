package gui;

import entitites.Item;

import javax.swing.*;

public class MainPresenter {

    private final DefaultListModel<Item> itemsModel = new DefaultListModel<>();

    public void onNewItemCreated(Item item) {
        itemsModel.addElement(item);
    }

    public void removeItem(int position) {
        itemsModel.remove(position);
    }

    public DefaultListModel<Item> getItemsModel() {
        return itemsModel;
    }
}
