package gui.priceEditorDialog;

import entities.Material;
import util.DatabaseRepository;

import java.util.ArrayList;
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
        List<Material> databaseMaterialsList = getAllMaterials();
        List<Material> changedMaterialList = materialTableModel.getChangedMaterialPriceList();
        List<Material> editedMaterials = new ArrayList<>();

        if (changedMaterialList.size() > 0) {
            for (Material material :
                    changedMaterialList) {
                int materialIndex = material.getId();
                if (databaseMaterialsList.get(materialIndex).getPrice() != material.getPrice()) {
                    editedMaterials.add(material);
                }
            }
        }

        if (editedMaterials.size() > 0) {
            for (Material material :
                    editedMaterials) {
                databaseRepository.updateMaterial(material);
                System.out.println(material.getMaterialType().toString()
                        + " " + material.getMaterialName()
                        + " " + material.getPrice());
            }
        }
    }

    //Getters
    public MaterialTableModel getMaterialTableModel() {
        return materialTableModel;
    }


}
