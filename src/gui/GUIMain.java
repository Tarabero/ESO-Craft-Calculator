package gui;

import entitites.Item;
import view.ToggleSelectionModel;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUIMain extends JFrame{
    //Window components
    private JPanel panelMain;
    private JButton btnAddItem;
    private JList<Item> listItems;
    private JButton btnRemoveItem;

    //Variables
    private DefaultListModel<Item> listItemsModel = new DefaultListModel<>();

    private int selectedPosition = -1;

    private final MainPresenter presenter;

    public GUIMain(MainPresenter presenter) {
        super("ESO Crafting Calculator v.0");
        this.presenter = presenter;
        setContentPane(this.panelMain);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();

        listItems.setSelectionModel(new ToggleSelectionModel());
        listItems.setCellRenderer(new ItemLayout(new BorderLayout()));
        listItems.setModel(listItemsModel);

        listItems.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    onSelectionChanged();
                }
            }
        });

        btnRemoveItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (hasSelection()){
                    removeItem();
                }
            }
        });

        btnAddItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addItems();
            }
        });

        panelMain.setSize(1000, 1000);
    }

    private void onSelectionChanged(){
        selectedPosition = listItems.getSelectedIndex();
    }

    private void removeItem(){
        listItemsModel.remove(selectedPosition);
    }

    private boolean hasSelection(){
        return selectedPosition != -1;
    }

    private void addItems(){
        for (Item item : presenter.getItemsList()) {
            listItemsModel.addElement(item);
        }
    }
}
