package gui.dialog.newitem;

import entities.Item;
import entities.QualityType;
import entities.Trait;
import entities.TraitType;
import entities.armor.ArmorType;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class NewItemDialog extends JDialog {
    private static final String NEW_ITEM_DIALOG_TITLE = "Specify Item";

    private JPanel contentPane;
    private JButton btnAddItem;
    private JButton btnCancel;
    private JComboBox<TraitType> comboItemType;
    private JComboBox<Object> comboItemSlot;
    private JComboBox<ArmorType> comboArmorType;
    private JComboBox<Trait> comboTrait;
    private JComboBox<QualityType> comboQuality;

    private final NewItemDialogPresenter presenter;
    private final NewItemDialogActionListener listener;

    public interface NewItemDialogActionListener {
        void onItemCreated(Item item);
    }

    public NewItemDialog(NewItemDialogPresenter presenter, NewItemDialogActionListener listener) {
        this.presenter = presenter;
        this.listener = listener;
        setupBasicElements();
        setupButtonListeners();
        setupComboBoxes();
    }

    private void setupBasicElements() {
        setTitle(NEW_ITEM_DIALOG_TITLE);
        setContentPane(contentPane);
        getRootPane().setDefaultButton(btnAddItem);
        setModalityType(Dialog.DEFAULT_MODALITY_TYPE);
    }

    private void setupButtonListeners() {
        btnAddItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onAddItemAction();
            }
        });

        btnCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancelAction();
            }
        });
        setupAlternativeCancelListeners();
    }

    private void onAddItemAction() {
        listener.onItemCreated(presenter.createItem());
        dispose();
    }

    private void onCancelAction() {
        dispose();
    }

    private void setupAlternativeCancelListeners() {
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        // call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancelAction();
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

    private void setupOtherComboBoxes() {
        comboArmorType.setModel(presenter.getComboArmorTypeModel());
        comboTrait.setModel(presenter.getComboTraitModel());
        comboQuality.setModel(presenter.getComboQualityModel());
    }

    private void shieldArmorSlotCheck() {
        comboArmorType.setVisible(presenter.shouldArmorTypeBeVisible());
        pack();
    }
}