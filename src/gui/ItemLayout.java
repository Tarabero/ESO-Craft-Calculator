package gui;

import entitites.CraftResource;
import entitites.Item;
import entitites.Material;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class ItemLayout extends JPanel implements ListCellRenderer<Item> {
    private JLabel itemName;
    private JTextField materialListDescription;
    private JPanel materialsListContainer;
    private JPanel container;
    private int lastSelected = -1;

    public ItemLayout(LayoutManager layout) {
        super(layout);
        setVisible(true);
        add(container);
    }

    public ItemLayout(Item item){
        super(new BorderLayout());
        itemName.setText(item.toString());
        itemName.setVisible(true);
        setMaterialsDescription(item.getAllCraftingResources());
        add(container);
    }

    @Override
    public Component getListCellRendererComponent(JList<? extends Item> list, Item value, final int index, boolean isSelected, boolean cellHasFocus) {
        itemName.setText(value.toString());
        setMaterialsDescription(value.getAllCraftingResources());

        if (isSelected) {
            materialListDescription.setBackground(list.getSelectionBackground());
            materialListDescription.setForeground(list.getSelectionForeground());
            lastSelected = index;
        }
        if (!isSelected){
            materialListDescription.setBackground(list.getBackground());
            materialListDescription.setForeground(list.getForeground());
        }
        return this;
    }

    private void setMaterialsDescription(List<CraftResource> resources){
        StringBuilder stringBuilder = new StringBuilder();
        for (CraftResource craftResource :
                resources) {
            stringBuilder.append(craftResource.toString());
            stringBuilder.append(", ");
        }
        materialListDescription.setText(stringBuilder.toString());
    }

    public void setMaterialsVisible(boolean isVisible){
        materialsListContainer.setVisible(isVisible);
    }
}
