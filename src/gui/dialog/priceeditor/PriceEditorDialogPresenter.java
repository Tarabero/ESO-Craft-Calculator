package gui.dialog.priceeditor;

import entities.Material;
import util.DatabaseRepository;
import util.MaterialCache;
import util.TTCParser;

import javax.swing.*;
import java.io.FileNotFoundException;
import java.util.List;

public class PriceEditorDialogPresenter {

    private final DatabaseRepository databaseRepository;
    private MaterialTableModel materialTableModel;
    private final List<Material> listMaterials;

    public PriceEditorDialogPresenter(final DatabaseRepository databaseRepository) {
        this.databaseRepository = databaseRepository;
        listMaterials = getAllMaterials();
        setupMaterialTableModel();
    }

    public void setupMaterialTableModel() {
        materialTableModel = new MaterialTableModel(listMaterials);
    }

    public void updateTableWithTTCPrices(String ttcPriceListPath) throws FileNotFoundException {
        if (ttcPriceListPath.endsWith(".lua")) {
            TTCParser parser = new TTCParser(ttcPriceListPath);
            if (parser.chosenFileIsTTCPriceTable()) {
                int materialsQuantity = listMaterials.size();
                int successfullyEdited = 0;
                for (Material material :
                        listMaterials) {
                    int ttcID = material.getTtcId();
                    int newPrice = (int) Math.round(parser.getSuggestedPriceFromTTCFor(ttcID));
                    if (newPrice == -1) {
                        continue;
                    }
                    material.setPrice(newPrice);
                    successfullyEdited++;
                }
                materialTableModel.updateMaterials(listMaterials);
                fireSuccessfulMaterialsPriceTTCUpdate(successfullyEdited, materialsQuantity);
                parser = null; //cleaning instance
            } else fireWrongFileDialog();
        } else fireWrongFileDialog();
    }

    private void fireSuccessfulMaterialsPriceTTCUpdate(int successfullyEdited, int overallQuantity) {
        String message = "%1$s of %2$s materials updated successfully!";
        JOptionPane.showMessageDialog(null,
                String.format(message, successfullyEdited, overallQuantity),
                "Price import successful",
                JOptionPane.PLAIN_MESSAGE);
    }

    private void fireWrongFileDialog() {
        JOptionPane.showMessageDialog(null,
                "This is not a valid file!",
                "File error",
                JOptionPane.WARNING_MESSAGE);
    }

    private List<Material> getAllMaterials() {
        return databaseRepository.getAllMaterials();
    }

    public void updateMaterialsPrice() {
        List<Material> editedMaterials = materialTableModel.getChangedMaterialPriceList();
        updateMaterialsData(editedMaterials);
    }

    private void updateMaterialsData(List<Material> editedMaterials) {
        consoleMaterialsOutLog("MATERIALS EDITED:", editedMaterials);
        for (Material material :
                editedMaterials) {
            MaterialCache materialCache = MaterialCache.getInstance();
            if (materialCache.contains(material)) {
                materialCache.update(material);
            }
            databaseRepository.updateMaterial(material);
        }
    }

    private void consoleMaterialsOutLog(String header, List<Material> materialList) {
        System.out.println(header);
        for (Material material :
                materialList) {
            System.out.println(material.getMaterialType().toString()
                    + " " + material.getMaterialName()
                    + " " + material.getPrice());
        }
    }

    public boolean hasPriceChanged() {
        return !materialTableModel.getChangedMaterialPriceList().isEmpty();
    }

    public MaterialTableModel getMaterialTableModel() {
        return materialTableModel;
    }
}
