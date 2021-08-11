package gui;

import db.DatabaseHelper;
import db.dao.MaterialDaoImpl;
import db.dao.interfaces.MaterialDao;
import entitites.CraftResource;
import entitites.QualityType;
import entitites.Workbench;
import util.QualityResourceCollector;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.util.List;

public class GUIMain extends JFrame{
    //Window components
    private JPanel panelMain;
    private JButton btnAddItem;
    private JList listItems;
    private JButton btnRemoveItem;

    //Variables
    private LinkedList<String> itemComponents;
    private LinkedList<String> lastAddedComponents;
    private DefaultListModel listItemsModel;


    public GUIMain() {
        super("ESO Crafting Calculator v.0");
        this.setContentPane(this.panelMain);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        itemComponents = new LinkedList<String>();
        lastAddedComponents = new LinkedList<String>();
        listItemsModel = new DefaultListModel();
        listItems.setModel(listItemsModel);

        btnAddItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                BtnAddItemAction();
            }
        });

        btnRemoveItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                BtnRemoveItemAction();
            }
        });

        listItems.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {

            }
        });

    }

    private void BtnAddItemAction() {
        AddItemComponent(QualityType.LEGENDARY, Workbench.JEWELRY);
    }

    private void AddItemComponent (QualityType qualityType, Workbench workbench) {
        List<CraftResource> outputList = GetCraftResourceListFor(qualityType, workbench);
        String output = "";
        lastAddedComponents.clear();
        for (int i = 0; i < outputList.size(); i++) {
            output = outputList.get(i).getMaterial().toString() + " " + outputList.get(i).getQuantity();
            System.out.println("Adding huge pile of Shit named: " + output);
            itemComponents.add(output);
            lastAddedComponents.add(output);

        }
        refreshListItems();
    }

    public void refreshListItems() {
        listItemsModel.removeAllElements();
        for (String str:itemComponents) {
            listItemsModel.addElement(str);
        }
    }

    private List<CraftResource> GetCraftResourceListFor (QualityType qualityType, Workbench workbench) {
        DatabaseHelper databaseHelper = DatabaseHelper.getInstance();
        databaseHelper.connect();
        //
        MaterialDao materialDao = new MaterialDaoImpl(databaseHelper);
        QualityResourceCollector qualityResourceCollector = new QualityResourceCollector(materialDao);
        //
        List<CraftResource> craftResources = qualityResourceCollector.getResourcesFor(qualityType, workbench);
        databaseHelper.close();
        return craftResources;
    }

    private void BtnRemoveItemAction () {
        RemoveLastItemComponent();;
    }

    private void RemoveLastItemComponent() {
        if (lastAddedComponents.size()>0 && itemComponents.size() >= lastAddedComponents.size()) {
            for (int i = 1; i == lastAddedComponents.size(); i++) {
                System.out.println("Removing element: "+ itemComponents.get(itemComponents.size() - i));
                itemComponents.remove(itemComponents.size() - i);
            }
            refreshListItems();
        }
        else {
            System.out.println("Error, can't delete");
        }
    }


}
