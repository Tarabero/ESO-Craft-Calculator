package gui;

import db.DatabaseHelper;
import gui.dialog.newitem.NewItemDialog;
import gui.dialog.newitem.NewItemDialogPresenter;
import gui.dialog.priceeditor.PriceEditorDialog;
import gui.dialog.priceeditor.PriceEditorDialogPresenter;
import gui.main.MainScreen;
import gui.main.MainScreenActionListener;
import gui.main.MainScreenPresenter;
import util.DatabaseRepository;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class WindowController implements MainScreenActionListener {

    private final DatabaseHelper databaseHelper;
    private final DatabaseRepository databaseRepository;
    private NewItemDialogPresenter newItemDialogPresenter;

    private final WindowListener windowListener = new WindowListener() {
        @Override
        public void windowOpened(WindowEvent e) {

        }

        @Override
        public void windowClosing(WindowEvent e) {
            mainScreenClosingAction();
        }

        @Override
        public void windowClosed(WindowEvent e) {

        }

        @Override
        public void windowIconified(WindowEvent e) {

        }

        @Override
        public void windowDeiconified(WindowEvent e) {

        }

        @Override
        public void windowActivated(WindowEvent e) {

        }

        @Override
        public void windowDeactivated(WindowEvent e) {

        }
    };

    public WindowController() {
        databaseHelper = DatabaseHelper.getInstance();
        databaseHelper.connect();
        databaseRepository = new DatabaseRepository(databaseHelper);
        openMainScreen();
    }

    private void mainScreenClosingAction() {
        databaseHelper.close();
    }

    private void openMainScreen() {
        MainScreen mainScreenWindow = new MainScreen(new MainScreenPresenter(), this);
        mainScreenWindow.addWindowListener(windowListener);
        mainScreenWindow.setVisible(true);
        mainScreenWindow.pack();
    }

    @Override
    public void startNewItemDialog(NewItemDialog.NewItemDialogActionListener listener) {
        openNewItemDialog(listener);
    }

    private void openNewItemDialog(NewItemDialog.NewItemDialogActionListener listener) {
        if (newItemDialogPresenter == null) {
            newItemDialogPresenter = new NewItemDialogPresenter(databaseRepository);
        }
        NewItemDialog newItemDialog = new NewItemDialog(newItemDialogPresenter, listener);
        newItemDialog.pack();
        newItemDialog.setVisible(true);
    }

    @Override
    public void startPriceEditorDialog(PriceEditorDialog.PriceEditorDialogActionListener listener) {
        openPriceEditorDialog(listener);
    }

    private void openPriceEditorDialog(PriceEditorDialog.PriceEditorDialogActionListener listener) {
        PriceEditorDialogPresenter presenter = new PriceEditorDialogPresenter(databaseRepository);
        PriceEditorDialog dialog = new PriceEditorDialog(presenter, listener);
        dialog.pack();
        dialog.setVisible(true);
    }
}
