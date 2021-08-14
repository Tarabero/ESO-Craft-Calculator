package gui.main;

import gui.dialog.CreateItemDialog;
import gui.list.ItemLayout;
import gui.list.ToggleSelectionModel;
import item.Item;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainScreen extends JFrame {
    //Window components
    private JPanel panelMain;
    private JButton btnAddItem;
    private JList<Item> listItems;
    private JButton btnRemoveItem;
    private JList listAllMaterials;
    private JLabel labelTotal;

    private int selectedPosition = -1;
    private final MainPresenter presenter;

    private MainScreenActionsListener mainScreenActionsListener;

    public interface MainScreenActionsListener {
        void openItemCreationDialog(CreateItemDialog.CreateItemDialogActionListener itemCreationListener);
    }

    public MainScreen(MainPresenter presenter, MainScreenActionsListener mainScreenActionsListener) {
        super("ESO Crafting Calculator v.0");
        this.presenter = presenter;
        this.mainScreenActionsListener = mainScreenActionsListener;
        setContentPane(this.panelMain);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setupItemsList();
        setupMaterialsList();
        setupButtons();
        updateTotalLabel();
        pack();
    }

    private void setupItemsList() {
        listItems.setSelectionModel(new ToggleSelectionModel());
        listItems.setCellRenderer(new ItemLayout());
        listItems.setModel(presenter.getItemsModel());
        listItems.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    onSelectionChanged();
                }
            }
        });
    }

    private void setupMaterialsList() {
        listAllMaterials.setModel(presenter.getMaterialsModel());
    }

    private void onSelectionChanged() {
        selectedPosition = listItems.getSelectedIndex();
    }

    private void setupButtons() {
        btnRemoveItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (hasSelection()) {
                    removeItem();
                }
            }
        });
        btnAddItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openCreateNewItemDialog();
            }
        });
    }

    private boolean hasSelection() {
        return selectedPosition != -1;
    }

    private void removeItem() {
        presenter.removeItem(selectedPosition);
        updateTotalLabel();
    }

    private void updateTotalLabel() {
        labelTotal.setText(presenter.getTotalPrice());
    }

    private void openCreateNewItemDialog() {
        mainScreenActionsListener.openItemCreationDialog(new CreateItemDialog.CreateItemDialogActionListener() {
            @Override
            public void onItemCreated(Item item) {
                if (item != null) {
                    presenter.onNewItemCreated(item);
                    updateTotalLabel();
                }
            }
        });
    }
}
