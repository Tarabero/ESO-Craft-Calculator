package gui.main;

import gui.dialog.newitem.NewItemDialog;
import gui.dialog.priceeditor.PriceEditorDialog;

public interface MainScreenActionListener {
    void startNewItemDialog(NewItemDialog.NewItemDialogActionListener listener);

    void startPriceEditorDialog(PriceEditorDialog.PriceEditorDialogActionListener listener);
}
