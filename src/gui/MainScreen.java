package gui;

import entitites.CraftResource;
import entitites.Item;
import gui.renderers.CraftResourceListRenderer;
import gui.renderers.ItemListRenderer;
import util.GlobalConstants;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class MainScreen extends JFrame {
    private JScrollPane listItemContainer;
    private JScrollPane listCraftResourceContainer;
    private JPanel btnContainer;
    private JPanel miscContainer;

    private JPanel panelMain;
    private JButton btnAddItem;
    private JButton btnRemoveItem;
    private JList<CraftResource> listCraftResources;
    private JList<Item> listItems;
    private JLabel totalPrice;
    private JLabel goldIcon;
    private JLabel totalPriceLabel;

    private int selectedPosition = -1;
    private final MainScreenPresenter presenter;

    public MainScreen(final MainScreenPresenter mainScreenPresenter) {
        presenter = mainScreenPresenter;
        setupBasicElements();
        setupWindowClothingListener();
        setupListItems();
        setupListCraftResources();
        setupTotalPrice();
        setupButtonListeners();

    }

    private void setupBasicElements() {
        setTitle("ESO Crafting Calculator v.00");
        setContentPane(this.panelMain);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        pack();
    }

    private void setupWindowClothingListener() {
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                presenter.eventsOnMainScreenExit();
                e.getWindow().dispose();
            }
        });
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
        goldIcon.setIcon(new ImageIcon(GlobalConstants.UI_ICON_GOLD_IMAGE_PATH));
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
    }

    private void btnAddItemAction() {
        Item item = presenter.getRandomItem();
        presenter.addItemToItemList(item);
        updateTotalPrice();
    }

    private void updateTotalPrice() {
        totalPrice.setText(presenter.getTotalPriceCounter().toString());
    }

    private void btnRemoveItemAction() {
        presenter.removeItemFromItemList(selectedPosition);
        updateTotalPrice();
    }
}
