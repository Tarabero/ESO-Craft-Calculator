package gui.renderers;

import entitites.CraftResource;
import util.GlobalConstants;

import javax.swing.*;
import java.awt.*;

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
        ImageIcon craftResourceIcon = new ImageIcon(craftResource.getMaterial().getMaterialIconImagePath());
        craftResourceField.setIcon(craftResourceIcon);
        craftResourceField.setText(craftResource.toString());
    }

    private void setupPriceField(CraftResource craftResource) {
        priceField.setIcon(new ImageIcon(GlobalConstants.UI_ICON_GOLD_IMAGE_PATH));
        priceField.setText("Price: " + craftResource.getTotalPrice());
    }

    private void setSelectionColours(Color background, Color foreground) {
        container.setBackground(background);
        container.setForeground(foreground);
    }
}
