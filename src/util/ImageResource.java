package util;

import javax.swing.*;
import java.net.URL;

public class ImageResource {

    private static ImageIcon goldIcon;

    public static ImageIcon getGoldIconImage() {
        if (goldIcon == null) {
            setGoldIconImage();
            return goldIcon;
        }
        return goldIcon;
    }

    private static void setGoldIconImage() {
        URL goldIconImagePath = ImageResource.class.getResource(GlobalConstants.UI_ICON_GOLD_IMAGE_PATH);
        if (goldIconImagePath != null) {
            goldIcon = new ImageIcon(goldIconImagePath);
        } else goldIcon = new ImageIcon();
    }
}
