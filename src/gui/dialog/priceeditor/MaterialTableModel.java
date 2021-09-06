package gui.dialog.priceeditor;

import entities.Material;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

public class MaterialTableModel extends AbstractTableModel {
    private final List<Material> materialList;
    private final List<Material> changedMaterialsList = new ArrayList<>();

    private final int materialColumnIndex = 0;
    private final int materialTypeColumnIndex = 1;
    private final int priceColumnIndex = 2;

    private final String[] columnNames = new String[]{
            "Material", "Material Type", "Price"
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
            case materialColumnIndex:
                return row.getMaterialName();
            case materialTypeColumnIndex:
                return row.getMaterialType().toString();
            case priceColumnIndex:
                return row.getPrice();
        }
        return null;
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        Material row = materialList.get(rowIndex);
        if (columnIndex == priceColumnIndex) {
            row.setPrice((Integer) aValue);
        }
        noteTheChange(getMaterialAt(rowIndex));
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return columnIndex == priceColumnIndex;
    }

    private void noteTheChange(Material material) {
        if (changedMaterialsList.contains(material)) {
            int materialIndex = changedMaterialsList.indexOf(material);
            changedMaterialsList.set(materialIndex, material);
        } else {
            changedMaterialsList.add(material);
        }
    }

    private Material getMaterialAt(int rowIndex) {
        return materialList.get(rowIndex);
    }

    public List<Material> getChangedMaterialPriceList() {
        return changedMaterialsList;
    }
}
