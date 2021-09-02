package gui.priceEditorDialog;

import entities.Material;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

public class MaterialTableModel extends AbstractTableModel {
    private final List<Material> materialList;
    private final List<Material> changedMaterialsList = new ArrayList<>();

    private final String[] columnNames = new String[]{
            "Material Type", "Material", "Price"
    };

    private final Class[] columnClass = new Class[]{
            String.class, String.class, Integer.class
    };

    public MaterialTableModel(List<Material> materialList) {
        this.materialList = materialList;
    }

    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return columnClass[columnIndex];
    }

    @Override
    public int getRowCount() {
        return materialList.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Material row = materialList.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return row.getMaterialType().toString();
            case 1:
                return row.getMaterialName();
            case 2:
                return row.getPrice();
        }
        return null;
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        Material row = materialList.get(rowIndex);
        if (columnIndex == 2) {
            row.setPrice((Integer) aValue);
        }
        changedMaterialsList.add(getMaterialAt(rowIndex));
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return columnIndex == 2;
    }

    private Material getMaterialAt(int rowIndex) {
        return materialList.get(rowIndex);
    }

    public List<Material> getChangedMaterialPriceList() {
        return changedMaterialsList;
    }
}
