package gui.priceEditorDialog;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class PriceEditorDialog extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTable materialsTable;

    private final PriceEditorDialogPresenter presenter;
    private final PriceEditorDialogActionListener listener;

    public interface PriceEditorDialogActionListener {
        void PriceConfirmedAction();
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
                btnConfirmAction();
            }
        });

        buttonCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                btnCancelAction();
            }
        });
        setupAlternativeCancelListeners();
    }

    private void btnConfirmAction() {
        presenter.updateMaterialsPrice();
        listener.PriceConfirmedAction();
        dispose();
    }

    private void btnCancelAction() {
        dispose();
    }

    private void setupAlternativeCancelListeners() {
        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                btnCancelAction();
            }
        });

        // call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                btnCancelAction();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
    }

    private void setupTable() {
        presenter.updateMaterialTableModel();
        materialsTable.setModel(presenter.getMaterialTableModel());
    }
}
