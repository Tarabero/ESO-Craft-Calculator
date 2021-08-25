package gui.renderers;

import entities.Item;
import util.GlobalConstants;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class ItemListRenderer extends JPanel implements ListCellRenderer<Item> {
    private JPanel container;
    private JPanel nameContainer;

    private JLabel priceField;
    private JTextField itemCraftResourcesField;
    private JLabel itemField;

    public ItemListRenderer() {
        super(new BorderLayout());
        add(container);
    }

    @Override
    public Component getListCellRendererComponent(JList<? extends Item> list, Item item, int index, boolean isSelected, boolean cellHasFocus) {
        setupPriceField(item);
        setupItemField(item);
        setupCraftResourcesField(item);
        if (isSelected) {
            setSelectionColours(list.getSelectionBackground(), list.getSelectionForeground());
        } else {
            setSelectionColours(list.getBackground(), list.getForeground());
        }

        return this;
    }

    private void setupPriceField(Item item) {
        URL goldIconPath = getClass().getResource(GlobalConstants.UI_ICON_GOLD_IMAGE_PATH);
        priceField.setIcon(new ImageIcon(goldIconPath));
        priceField.setText("Cost: " + item.getPrice());
    }

    private void setupItemField(Item item) {
        URL itemIconPath = getClass().getResource(item.getItemIconImagePath());
        itemField.setIcon(new ImageIcon(itemIconPath));
        itemField.setText(item.toString());
    }

    private void setupCraftResourcesField(Item item) {
        String itemMaterialsUsed = item.getAllCraftingResources().toString().substring(1, item.getAllCraftingResources().toString().length() - 1);
        itemCraftResourcesField.setText(itemMaterialsUsed);
    }

    private void setSelectionColours(Color background, Color foreground) {
        nameContainer.setBackground(background);
        nameContainer.setForeground(foreground);
        itemCraftResourcesField.setBackground(background);
        itemCraftResourcesField.setForeground(foreground);
    }
}


