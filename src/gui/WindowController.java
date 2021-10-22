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
import util.GlobalConstants;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.net.URL;

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

    private final DatabaseHelper.DatabaseHelperErrorListener databaseErrorListener = new DatabaseHelper.DatabaseHelperErrorListener() {
        @Override
        public void onError(String errorTitle, String errorMessage) {
            onDatabaseError(errorTitle, errorMessage);
        }
    };
    private final PriceEditorDialogPresenter.FileErrorListener filePickerErrorListener = new PriceEditorDialogPresenter.FileErrorListener() {
        @Override
        public void onError(String errorTitle, String errorMessage) {
            onFilePickerError(errorTitle, errorMessage);
        }
    };

    public WindowController() {
        databaseHelper = DatabaseHelper.getInstance();
        databaseHelper.setErrorListener(databaseErrorListener);
        databaseHelper.connect();
        databaseRepository = new DatabaseRepository(databaseHelper);
        setProgramLookAndFeelTheme(LookAndFeelThemes.WINDOWS);
        openMainScreenWindow();
    }

    private void onDatabaseError(String errorTitle, String errorMessage) {
        showErrorDialog(errorTitle, errorMessage);
        System.exit(1);
    }

    private void showErrorDialog(String errorTitle, String errorMessage) {
        JOptionPane.showMessageDialog(null,
                errorMessage,
                errorTitle,
                JOptionPane.WARNING_MESSAGE);
    }

    private void onFilePickerError(String errorTitle, String errorMessage) {
        showErrorDialog(errorTitle, errorMessage);
    }

    private void mainScreenClosingAction() {
        databaseHelper.close();
    }

    private void setProgramLookAndFeelTheme(LookAndFeelThemes theme) {
        try {
            UIManager.setLookAndFeel(getLookAndFeelTheme(theme));
        } catch (UnsupportedLookAndFeelException | IllegalAccessException | InstantiationException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private String getLookAndFeelTheme(LookAndFeelThemes theme) {
        switch (theme) {
            case NIMBUS:
                return LookAndFeelThemes.NIMBUS.getThemeCall();
            case MOTIF:
                return LookAndFeelThemes.MOTIF.getThemeCall();
            case WINDOWS:
                return LookAndFeelThemes.WINDOWS.getThemeCall();
            case WINDOWS_CLASSIC:
                return LookAndFeelThemes.WINDOWS_CLASSIC.getThemeCall();
            case METAL:
            default: // return cross-platform default Java Metal theme on default
                return LookAndFeelThemes.METAL.getThemeCall();
        }
    }

    private void openMainScreenWindow() {
        MainScreen window = new MainScreen(new MainScreenPresenter(), this);
        window.addWindowListener(windowListener);
        window.setLocationRelativeTo(null);
        setWindowIcon(window);
        window.pack();
        window.setVisible(true);
    }

    private void setWindowIcon(Window window) {
        URL programIcon = getClass().getClassLoader().getResource(GlobalConstants.UI_ICON_PROGRAM_PATH);
        if (programIcon != null) {
            window.setIconImage(new ImageIcon(programIcon).getImage());
        }
    }

    @Override
    public void startNewItemDialog(NewItemDialog.NewItemDialogActionListener listener) {
        openNewItemDialog(listener);
    }

    private void openNewItemDialog(NewItemDialog.NewItemDialogActionListener listener) {
        if (newItemDialogPresenter == null) {
            newItemDialogPresenter = new NewItemDialogPresenter(databaseRepository);
        }
        NewItemDialog dialog = new NewItemDialog(newItemDialogPresenter, listener);
        setWindowIcon(dialog);
        dialog.setLocationRelativeTo(null);
        dialog.pack();
        dialog.setVisible(true);
    }

    @Override
    public void startPriceEditorDialog(PriceEditorDialog.PriceEditorDialogActionListener listener) {
        openPriceEditorDialog(listener);
    }

    private void openPriceEditorDialog(PriceEditorDialog.PriceEditorDialogActionListener listener) {
        PriceEditorDialogPresenter presenter = new PriceEditorDialogPresenter(databaseRepository, filePickerErrorListener);
        PriceEditorDialog dialog = new PriceEditorDialog(presenter, listener);
        setWindowIcon(dialog);
        dialog.setLocationRelativeTo(null);
        dialog.pack();
        dialog.setVisible(true);
    }
}
