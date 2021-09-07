package gui.dialog.priceeditor;

import entities.Material;
import util.DatabaseRepository;
import util.MaterialCache;

import java.util.List;

public class PriceEditorDialogPresenter {

    private final DatabaseRepository databaseRepository;
    private MaterialTableModel materialTableModel;

    public PriceEditorDialogPresenter(final DatabaseRepository databaseRepository) {
        this.databaseRepository = databaseRepository;
        updateMaterialTableModel();
    }

    public void updateMaterialTableModel() {
        List<Material> materialsList = getAllMaterials();
        materialTableModel = new MaterialTableModel(materialsList);
    }

    private List<Material> getAllMaterials() {
        return databaseRepository.getAllMaterials();
    }

    public void updateMaterialsPrice() {
        List<Material> editedMaterials = materialTableModel.getChangedMaterialPriceList();
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
