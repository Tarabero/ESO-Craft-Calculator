package gui.main;

import entities.CraftResource;
import entities.Item;
import gui.dialog.newitem.NewItemDialog;
import gui.dialog.priceeditor.PriceEditorDialog;
import gui.renderers.CraftResourceListRenderer;
import gui.renderers.ItemListRenderer;
import util.GlobalConstants;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

public class MainScreen extends JFrame {
    private JPanel panelMain;
    private JButton btnAddItem;
    private JButton btnRemoveItem;
    private JButton btnPriceEditor;
    private JList<CraftResource> listCraftResources;
    private JList<Item> listItems;
    private JLabel totalPrice;
    private JLabel goldIcon;
    private JLabel totalPriceLabel;
    private JSpinner spinnerSurplusValue;

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
        setTitle("ESO Crafting Calculator v0.01 (Alpha)");
        setContentPane(panelMain);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void setupListItems() {
        listItems.setModel(presenter.getItemsModel());
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
        URL goldIconPath = getClass().getResource(GlobalConstants.UI_ICON_GOLD_IMAGE_PATH);
        goldIcon.setIcon(new ImageIcon(goldIconPath));
        totalPrice.setText(presenter.getTotalPriceCounter().toString());
    }

    private void setupButtonListeners() {
        btnAddItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onAddItemAction();
            }
        });

        btnRemoveItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onRemoveItemAction();
            }
        });

        btnPriceEditor.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onPriceEditorCall();
            }
        });
    }

    private void onAddItemAction() {
        openNewItemDialog();
    }

    private void openNewItemDialog() {
        listener.startNewItemDialog(new NewItemDialog.NewItemDialogActionListener() {
            @Override
            public void itemCreationAction(Item item) {
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

    private void updateTotalPrice() {
        totalPrice.setText(presenter.getTotalPriceCounter().toString());
    }

    private void onPriceEditorCall() {
        openPriceEditorDialog();
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
