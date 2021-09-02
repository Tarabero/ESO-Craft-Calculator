package gui.main;

import entities.CraftResource;
import entities.Item;
import gui.newItemDialog.NewItemDialog;
import gui.priceEditorDialog.PriceEditorDialog;
import gui.renderers.CraftResourceListRenderer;
import gui.renderers.ItemListRenderer;
import util.GlobalConstants;

import javax.swing.*;
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

    private int selectedPosition = -1;
    private final MainScreenPresenter presenter;
    private final MainScreenActionListener listener;

    public MainScreen(final MainScreenPresenter presenter, final MainScreenActionListener listener) {
        this.presenter = presenter;
        this.listener = listener;
        setupBasicElements();
        setupListItems();
        setupListCraftResources();
        setupTotalPrice();
        setupButtonListeners();
    }

    private void setupBasicElements() {
        setTitle("ESO Crafting Calculator v.00");
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
                btnAddItemAction();
            }
        });

        btnRemoveItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                btnRemoveItemAction();
            }
        });

        btnPriceEditor.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                btnPriceEditorAction();
            }
        });
    }

    private void btnAddItemAction() {
        openNewItemDialog();
    }

    private void btnPriceEditorAction() {
        openPriceEditorDialog();
    }

    private void updateTotalPrice() {
        totalPrice.setText(presenter.getTotalPriceCounter().toString());
    }

    private void btnRemoveItemAction() {
        presenter.removeItemFromItemList(selectedPosition);
        updateTotalPrice();
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

    private void openPriceEditorDialog() {
        listener.startPriceEditorDialog(new PriceEditorDialog.PriceEditorDialogActionListener() {
            @Override
            public void PriceConfirmedAction() {

            }
        });
    }
}
