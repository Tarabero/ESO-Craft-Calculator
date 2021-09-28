package gui.renderers;

import entities.CraftResource;
import util.GlobalConstants;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class CraftResourceListRenderer extends JPanel implements ListCellRenderer<CraftResource> {
    private JPanel container;
    private JLabel craftResourceField;
    private JLabel priceField;

    public CraftResourceListRenderer() {
        super(new BorderLayout());
        add(container);
    }

    @Override
    public Component getListCellRendererComponent(JList<? extends CraftResource> list, CraftResource craftResource, int index, boolean isSelected, boolean cellHasFocus) {
        setupCraftResourceField(craftResource);
        setupPriceField(craftResource);
        if (isSelected) {
            setSelectionColours(list.getSelectionBackground(), list.getSelectionForeground());
        } else {
            setSelectionColours(list.getBackground(), list.getForeground());
        }
        return this;
    }

    private void setupCraftResourceField(CraftResource craftResource) {
        URL materialIconPath = getClass().getResource(craftResource.getMaterial().getMaterialIconImagePath());
        if (materialIconPath != null) {
            ImageIcon craftResourceIcon = new ImageIcon(materialIconPath);
            craftResourceField.setIcon(craftResourceIcon);
        }
        craftResourceField.setText(craftResource.toString());
    }

    private void setupPriceField(CraftResource craftResource) {
        URL goldIconPath = getClass().getResource(GlobalConstants.UI_ICON_GOLD_IMAGE_PATH);
        if (goldIconPath != null) {
            priceField.setIcon(new ImageIcon(goldIconPath));
        }
        priceField.setText(String.valueOf(craftResource.getTotalPrice()));
    }

    private void setSelectionColours(Color background, Color foreground) {
        container.setBackground(background);
        container.setForeground(foreground);
    }
}
