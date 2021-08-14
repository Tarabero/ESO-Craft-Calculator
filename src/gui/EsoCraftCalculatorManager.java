package gui;

import db.DatabaseHelper;
import gui.dialog.CreateItemDialog;
import gui.dialog.CreateItemDialog.CreateItemDialogActionListener;
import gui.dialog.CreateItemDialogPresenter;
import gui.main.MainPresenter;
import gui.main.MainScreen;
import gui.main.MainScreen.MainScreenActionsListener;
import repository.DatabaseRepository;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class EsoCraftCalculatorManager {

    private final DatabaseHelper databaseHelper;
    private final DatabaseRepository databaseRepository;
    private final MainScreenActionsListener mainScreenActionsListener = new MainScreenActionsListener() {
        @Override
        public void openItemCreationDialog(CreateItemDialogActionListener itemCreationListener) {
            openCreateItemDialog(itemCreationListener);
        }
    };
    private final WindowListener windowListener = new WindowListener() {
        @Override
        public void windowOpened(WindowEvent e) {

        }

        @Override
        public void windowClosing(WindowEvent e) {
            databaseHelper.close();
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

    public EsoCraftCalculatorManager() {
        databaseHelper = DatabaseHelper.getInstance();
        databaseHelper.connect();
        databaseRepository = new DatabaseRepository(databaseHelper);
    }

    public void startMainScreen() {
        MainScreen mainScreenWindow = new MainScreen(new MainPresenter(), mainScreenActionsListener);
        mainScreenWindow.pack();
        mainScreenWindow.setVisible(true);
        mainScreenWindow.addWindowListener(windowListener);
    }

    private CreateItemDialogPresenter dialogPresenter;

    private void openCreateItemDialog(CreateItemDialogActionListener itemCreationListener) {
        if (dialogPresenter == null) {
            dialogPresenter = new CreateItemDialogPresenter(databaseRepository);
        }
        CreateItemDialog dialog = new CreateItemDialog(dialogPresenter, itemCreationListener);
        dialog.pack();
        dialog.setVisible(true);
    }
}
