package gui.dialog.priceeditor;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileNotFoundException;

public class PriceEditorDialog extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTable materialsTable;
    private JButton buttonGetPricesFromTTC;

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
        setTitle("Material Price Editor");
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

        buttonGetPricesFromTTC.addActionListener(new ActionListener() {
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
        String dialogTitle = "Open Tamriel Trade Center price list";
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle(dialogTitle);
        fileChooser.setCurrentDirectory(new File(System.getProperty("user.dir")));
        FileFilter fileFilter = new FileFilter() {
            @Override
            public boolean accept(File f) {
                return f.getName().endsWith(".lua") || f.isDirectory();
            }

            @Override
            public String getDescription() {
                return "Lua source File (*.lua)";
            }
        };
        fileChooser.setFileFilter(fileFilter);
        fileChooser.setSelectedFile(new File("PriceTable.lua"));
        int result = fileChooser.showOpenDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            presenter.updateTableWithTTCPrices(selectedFile.getAbsolutePath());
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
