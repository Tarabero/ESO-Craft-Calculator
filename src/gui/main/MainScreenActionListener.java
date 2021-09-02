package gui.main;

import gui.newItemDialog.NewItemDialog;
import gui.priceEditorDialog.PriceEditorDialog;

public interface MainScreenActionListener {
    void startNewItemDialog(NewItemDialog.NewItemDialogActionListener listener);

    void startPriceEditorDialog(PriceEditorDialog.PriceEditorDialogActionListener listener);
}
