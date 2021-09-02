package gui;

import db.DatabaseHelper;
import gui.main.MainScreen;
import gui.main.MainScreenActionListener;
import gui.main.MainScreenPresenter;
import gui.newItemDialog.NewItemDialog;
import gui.newItemDialog.NewItemDialogPresenter;
import gui.priceEditorDialog.PriceEditorDialog;
import gui.priceEditorDialog.PriceEditorDialogPresenter;
import util.DatabaseRepository;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class WindowController implements MainScreenActionListener {

    private final DatabaseHelper databaseHelper;
    private final DatabaseRepository databaseRepository;
    private NewItemDialogPresenter newItemDialogPresenter;
    private PriceEditorDialogPresenter priceEditorDialogPresenter;

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

    @Override
    public void startNewItemDialog(NewItemDialog.NewItemDialogActionListener listener) {
        openNewItemDialog(listener);
    }

    @Override
    public void startPriceEditorDialog(PriceEditorDialog.PriceEditorDialogActionListener listener) {
        openPriceEditorDialog(listener);
    }

    private void openMainScreen() {
        MainScreen mainScreenWindow = new MainScreen(new MainScreenPresenter(), this);
        mainScreenWindow.addWindowListener(windowListener);
        mainScreenWindow.setVisible(true);
        mainScreenWindow.pack();
    }

    private void openNewItemDialog(NewItemDialog.NewItemDialogActionListener listener) {
        if (newItemDialogPresenter == null) {
            newItemDialogPresenter = new NewItemDialogPresenter(databaseRepository);
        }
        NewItemDialog newItemDialog = new NewItemDialog(newItemDialogPresenter, listener);
        newItemDialog.pack();
        newItemDialog.setVisible(true);
    }

    private void openPriceEditorDialog(PriceEditorDialog.PriceEditorDialogActionListener listener) {
        if (priceEditorDialogPresenter == null) {
            priceEditorDialogPresenter = new PriceEditorDialogPresenter(databaseRepository);
        }
        PriceEditorDialog dialog = new PriceEditorDialog(priceEditorDialogPresenter, listener);
        dialog.pack();
        dialog.setVisible(true);
    }

    private void mainScreenClosingAction() {
        databaseHelper.close();
    }
}
