package view;

import javax.swing.*;

public class ToggleSelectionModel extends DefaultListSelectionModel {
    private static final long serialVersionUID = 1L;

    boolean gestureStarted = false;

    @Override
    public void setSelectionInterval(int index0, int index1) {
        if(!gestureStarted){
            if (isSelectedIndex(index0)) {
                super.removeSelectionInterval(index0, index1);
            } else {
                super.addSelectionInterval(index0, index1);
            }
        }
        gestureStarted = true;
    }

    @Override
    public void setValueIsAdjusting(boolean isAdjusting) {
        if (!isAdjusting) {
            gestureStarted = false;
        }
    }
}
