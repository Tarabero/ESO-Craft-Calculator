package gui.dialog;

import entities.Item;
import entities.QualityType;
import entities.Trait;
import entities.TraitType;
import entities.armor.ArmorType;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class NewItemDialog extends JDialog {
    private JPanel contentPane;
    private JButton btnAddNewItem;
    private JButton btnCancel;
    private JComboBox<TraitType> comboItemType;
    private JComboBox<Object> comboItemSlot;
    private JComboBox<ArmorType> comboArmorType;
    private JComboBox<Trait> comboTrait;
    private JComboBox<QualityType> comboQuality;


    private final NewItemDialogPresenter presenter;
    private final NewItemDialogActionListener listener;

    public interface NewItemDialogActionListener {
        void itemCreationAction(Item item);
    }

    public NewItemDialog(NewItemDialogPresenter presenter, NewItemDialogActionListener listener) {
        this.presenter = presenter;
        this.listener = listener;
        setupBasicElements();
        setupButtonListeners();
        setupComboBoxes();
    }

    private void setupBasicElements() {
        setTitle("Specify Item");
        setContentPane(contentPane);
        getRootPane().setDefaultButton(btnAddNewItem);
        setModalityType(Dialog.DEFAULT_MODALITY_TYPE);
    }

    private void setupButtonListeners() {
        btnAddNewItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                btnAddNewItemAction();
            }
        });

        btnCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                btnCancelAction();
            }
        });
        setupAlternativeCancelListeners();
    }

    private void btnAddNewItemAction() {
        listener.itemCreationAction(presenter.createItem());
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

    private void setupComboBoxes() {
        setupItemTypeComboBox();
        setupItemSlotComboBox();
        setupOtherComboBoxes();
        shieldArmorSlotCheck();
    }

    private void setupItemTypeComboBox() {
        comboItemType.setModel(presenter.getComboItemTypeModel());
        comboItemType.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                updateComboBoxes();
            }
        });
    }

    private void updateComboBoxes() {
        presenter.onItemTypeChanged();
        comboArmorType.setVisible(presenter.shouldArmorTypeBeVisible());
        pack();
    }

    private void setupItemSlotComboBox() {
        comboItemSlot.setModel(presenter.getComboItemSlotModel());
        comboItemSlot.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                shieldArmorSlotCheck();
            }
        });
    }

    private void shieldArmorSlotCheck() {
        comboArmorType.setVisible(presenter.shouldArmorTypeBeVisible());
        pack();
    }

    private void setupOtherComboBoxes() {
        comboArmorType.setModel(presenter.getComboArmorTypeModel());
        comboTrait.setModel(presenter.getComboTraitModel());
        comboQuality.setModel(presenter.getComboQualityModel());
    }
}