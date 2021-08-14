package gui.list;

import item.CraftResource;
import item.Item;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class ItemLayout extends JPanel implements ListCellRenderer<Item> {
    private JLabel itemName;
    private JTextField materialListDescription;
    private JPanel materialsListContainer;
    private JPanel container;

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
        itemName.setText(value.toString().trim());
        setMaterialsDescription(value.getAllCraftingResources());

        if (isSelected) {
            changeBackgroundColor(list.getSelectionBackground(), list.getSelectionForeground());
        } else {
            changeBackgroundColor(list.getBackground(), list.getForeground());
        }
        return this;
    }

    private void changeBackgroundColor(Color background, Color foreground) {
        materialListDescription.setBackground(background);
        materialListDescription.setForeground(foreground);
    }

    private void setMaterialsDescription(List<CraftResource> resources) {
        StringBuilder stringBuilder = new StringBuilder();
        for (CraftResource craftResource :
                resources) {
            stringBuilder.append(craftResource.toString());
            stringBuilder.append(", ");
        }
        materialListDescription.setText(stringBuilder.toString());
    }

    public void setMaterialsVisible(boolean isVisible) {
        materialsListContainer.setVisible(isVisible);
    }
}
