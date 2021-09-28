package gui.dialog.priceeditor;

import entities.Material;
import util.DatabaseRepository;
import util.MaterialCache;
import util.TTCHelper;

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

    public void updateTableWithTTCPrices() throws FileNotFoundException {
        TTCHelper.updateMaterialsWithTTCPrices(listMaterials);
        materialTableModel.updateMaterials(listMaterials);

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
