package gui.dialog;

import entitites.Item;
import entitites.QualityType;
import entitites.Trait;
import entitites.TraitType;
import entitites.armor.ArmorType;

import javax.swing.*;
import java.awt.event.*;

public class CreateItemDialog extends JDialog {
    private CreateItemDialogPresenter presenter;

    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JComboBox<TraitType> itemTypeSelector;
    private JComboBox<Object> itemSlotSelector;
    private JComboBox<QualityType> itemQualitySelector;
    private JComboBox<Trait> itemTraitSelector;
    private JComboBox<ArmorType> armorTypeSelector;

    private final CreateItemDialogActionListener listener;

    public interface CreateItemDialogActionListener {
        void onItemCreated(Item item);
    }

    public CreateItemDialog(final CreateItemDialogPresenter presenter, CreateItemDialogActionListener listener) {
        this.presenter = presenter;
        this.listener = listener;
        setTitle("Create new Item");

        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);
        setupButtons();
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);

        setupItemTypeSelector();
        setupTraitSelector();
        setupQualitySelector();
        setupSlotSelector();
        setupArmorTypeSelector();
    }

    private void setupButtons() {
        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK();
            }
        });
        buttonCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        });
    }

    private void setupItemTypeSelector() {
        DefaultComboBoxModel<TraitType> model = presenter.getItemTypeModel();
        itemTypeSelector.setModel(model);
        itemTypeSelector.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    presenter.onItemTypeChanged();
                    updateUi();
                }
            }
        });
        updateUi();
    }

    private void updateUi() {
        armorTypeSelector.setVisible(presenter.shouldShowArmorTypes());
        pack();
    }

    private void setupTraitSelector() {
        itemTraitSelector.setModel(presenter.getItemTraitModel());
    }

    private void setupQualitySelector() {
        itemQualitySelector.setModel(presenter.getItemQualityModel());
        itemQualitySelector.setSelectedIndex(0);
    }

    private void setupSlotSelector() {
        itemSlotSelector.setModel(presenter.getItemSlotModel());
        itemSlotSelector.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                updateUi();
            }
        });
    }

    private void setupArmorTypeSelector() {
        armorTypeSelector.setModel(presenter.getArmorTypeModel());
        armorTypeSelector.setSelectedIndex(0);
    }

    private void onOK() {
        listener.onItemCreated(presenter.createItem());
        dispose();
    }

    private void onCancel() {
        dispose();
    }
}
