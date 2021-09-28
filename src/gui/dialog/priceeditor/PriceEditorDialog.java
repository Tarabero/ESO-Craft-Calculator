package gui.dialog.priceeditor;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileNotFoundException;

public class PriceEditorDialog extends JDialog {
    private static final String PRICE_EDITOR_WINDOW_TITLE = "Material Price Editor";
    private static final String DIRECTORY_PROPERTY = "user.dir";
    private static final String FILE_CHOOSER_DIALOG_TITLE = "Open Tamriel Trade Center price table";
    private static final String FILE_CHOOSER_DEFAULT_FILE_NAME = "PriceTable.lua";
    private static final String LUA_FILE_EXTENSION = ".lua";
    private static final String LUA_FILE_FILTER_DESCRIPTION = "Lua source File (*.lua)";

    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTable materialsTable;
    private JButton buttonGetPricesFromTtc;

    private final PriceEditorDialogPresenter presenter;
    private final PriceEditorDialogActionListener listener;

    public interface PriceEditorDialogActionListener {
        void onMaterialPriceChanged();
    }

    public PriceEditorDialog(PriceEditorDialogPresenter presenter, PriceEditorDialogActionListener listener) {
        this.presenter = presenter;
        this.listener = listener;
        setupBasicElements();
        setupButtonListeners();
        setupTable();
    }

    private void setupBasicElements() {
        setTitle(PRICE_EDITOR_WINDOW_TITLE);
        setContentPane(contentPane);
        getRootPane().setDefaultButton(buttonOK);
        setModalityType(Dialog.DEFAULT_MODALITY_TYPE);
    }

    private void setupButtonListeners() {
        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onConfirmAction();
            }
        });

        buttonGetPricesFromTtc.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    onTamrielTradeCenterDataCall();
                } catch (FileNotFoundException ex) {
                    ex.printStackTrace();
                }
            }
        });

        buttonCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancelAction();
            }
        });
        setupAlternativeCancelListeners();
    }

    private void onConfirmAction() {
        presenter.updateMaterialsPrice();
        if (presenter.hasPriceChanged()) {
            listener.onMaterialPriceChanged();
        }
        dispose();
    }

    private void onTamrielTradeCenterDataCall() throws FileNotFoundException {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle(FILE_CHOOSER_DIALOG_TITLE);
        fileChooser.setCurrentDirectory(new File(System.getProperty(DIRECTORY_PROPERTY)));
        FileFilter fileFilter = new FileFilter() {
            @Override
            public boolean accept(File f) {
                return f.getName().endsWith(LUA_FILE_EXTENSION) || f.isDirectory();
            }

            @Override
            public String getDescription() {
                return LUA_FILE_FILTER_DESCRIPTION;
            }
        };
        fileChooser.setFileFilter(fileFilter);
        fileChooser.setSelectedFile(new File(FILE_CHOOSER_DEFAULT_FILE_NAME));
        int result = fileChooser.showOpenDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            presenter.updateTableWithTtcPrices(selectedFile.getAbsolutePath());
        }
    }

    private void onCancelAction() {
        dispose();
    }

    private void setupAlternativeCancelListeners() {
        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancelAction();
            }
        });

        // call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancelAction();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
    }

    private void setupTable() {
        presenter.setupMaterialTableModel();
        materialsTable.setModel(presenter.getMaterialTableModel());
    }
}
