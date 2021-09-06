package gui.dialog.priceeditor;

import entities.Material;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class MaterialTableModel extends AbstractTableModel {
    private final static int MATERIAL_COLUMN_INDEX = 0;
    private final static int MATERIAL_TYPE_COLUMN_INDEX = 1;
    private final static int PRICE_COLUMN_INDEX = 2;

    private final List<Material> materialList;
    private final HashSet<Material> changedMaterials = new HashSet<>();

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
            case MATERIAL_COLUMN_INDEX:
                return row.getMaterialName();
            case MATERIAL_TYPE_COLUMN_INDEX:
                return row.getMaterialType().toString();
            case PRICE_COLUMN_INDEX:
                return row.getPrice();
        }
        return null;
    }

    @Override
    public void setValueAt(Object changedPrice, int rowIndex, int columnIndex) {
        Material material = materialList.get(rowIndex);
        if (columnIndex == PRICE_COLUMN_INDEX && material.getPrice() != (Integer) changedPrice) {
            material.setPrice((Integer) changedPrice);
            noteTheChange(getMaterialAt(rowIndex));
        }
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return columnIndex == PRICE_COLUMN_INDEX;
    }

    private void noteTheChange(Material material) {
        changedMaterials.remove(material);
        changedMaterials.add(material);
    }

    private Material getMaterialAt(int rowIndex) {
        return materialList.get(rowIndex);
    }

    public List<Material> getChangedMaterialPriceList() {
        return new ArrayList<>(changedMaterials);
    }
}
