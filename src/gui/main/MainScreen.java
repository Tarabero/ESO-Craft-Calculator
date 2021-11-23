package gui.main;

import entities.CraftResource;
import entities.Item;
import gui.dialog.newitem.NewItemDialog;
import gui.dialog.priceeditor.PriceEditorDialog;
import gui.renderers.CraftResourceListRenderer;
import gui.renderers.ItemListRenderer;
import gui.renderers.ToggleSelectionModel;
import util.ImageResource;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainScreen extends JFrame {
    private static final String MAIN_SCREEN_TITLE = "ESO Craft Calculator";

    private JPanel panelMain;
    private JButton btnAddItem;
    private JButton btnRemoveItem;
    private JButton btnPriceEditor;
    private JList<CraftResource> listCraftResources;
    private JList<Item> listItems;
    private JLabel totalPrice;
    private JLabel totalPriceLabel;
    private JLabel goldIcon;
    private JSpinner spinnerSurplusValue;
    private JButton btnRemoveAll;

    private int selectedPosition = -1;
    private final MainScreenPresenter presenter;
    private final MainScreenActionListener listener;

    public MainScreen(final MainScreenPresenter presenter, final MainScreenActionListener listener) {
        this.presenter = presenter;
        this.listener = listener;
        setupBasicElements();
        setupListItems();
        setupListCraftResources();
        setupSurplusValueSpinner();
        setupTotalPrice();
        setupButtonListeners();
    }

    private void setupBasicElements() {
        setTitle(MAIN_SCREEN_TITLE);
        setContentPane(panelMain);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void setupListItems() {
        listItems.setModel(presenter.getItemsModel());
        listItems.setSelectionModel(new ToggleSelectionModel());
        listItems.setCellRenderer(new ItemListRenderer());
        listItems.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    onSelectionChange();
                }
            }
        });
    }

    private void onSelectionChange() {
        selectedPosition = listItems.getSelectedIndex();
    }

    private void setupListCraftResources() {
        listCraftResources.setModel(presenter.getCraftResourcesModel());
        listCraftResources.setSelectionModel(new ToggleSelectionModel());
        listCraftResources.setCellRenderer(new CraftResourceListRenderer());
    }

    private void setupSurplusValueSpinner() {
        spinnerSurplusValue.setModel(presenter.getSpinnerModel());
        spinnerSurplusValue.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                updateTotalPrice();
            }
        });
    }

    private void setupTotalPrice() {
        goldIcon.setText("");
        goldIcon.setIcon(ImageResource.getGoldIconImage());
        totalPrice.setText(presenter.getTotalPriceCounter().toString());
    }

    private void setupButtonListeners() {
        btnAddItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openNewItemDialog();
            }
        });

        btnRemoveItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onRemoveItemAction();
            }
        });

        btnRemoveAll.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onRemoveAllAction();
            }
        });

        btnPriceEditor.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openPriceEditorDialog();
            }
        });
    }

    private void openNewItemDialog() {
        listener.startNewItemDialog(new NewItemDialog.NewItemDialogActionListener() {
            @Override
            public void onItemCreated(Item item) {
                if (item != null) {
                    presenter.addItemToItemList(item);
                    updateTotalPrice();
                }
            }
        });
    }

    private void onRemoveItemAction() {
        presenter.removeItemFromItemList(selectedPosition);
        updateTotalPrice();
    }

    private void onRemoveAllAction() {
        presenter.clearLists();
        updateTotalPrice();
    }

    private void updateTotalPrice() {
        totalPrice.setText(presenter.getTotalPriceCounter().toString());
    }

    private void openPriceEditorDialog() {
        listener.startPriceEditorDialog(new PriceEditorDialog.PriceEditorDialogActionListener() {
            @Override
            public void onMaterialPriceChanged() {
                refreshRenderers();
                presenter.recalculateTotalPrice();
                updateTotalPrice();
            }
        });
    }

    private void refreshRenderers() {
        listItems.setCellRenderer(new ItemListRenderer());
        listCraftResources.setCellRenderer(new CraftResourceListRenderer());
    }
}
