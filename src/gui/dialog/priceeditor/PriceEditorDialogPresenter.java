package gui.dialog.priceeditor;

import entities.Material;
import util.DatabaseRepository;
import util.MaterialCache;

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

    public void updateMaterialsPrice(PriceEditorDialog.PriceEditorDialogActionListener listener) {
//        List<Material> databaseMaterialsList = getAllMaterials();
        List<Material> changedMaterialList = materialTableModel.getChangedMaterialPriceList();
        List<Material> editedMaterials = new ArrayList<>();

        consoleMaterialsOutLog("MATERIALS RECEIVED FROM TABLE:", changedMaterialList);

        editedMaterials = changedMaterialList;

//        if (changedMaterialList.size() > 0) {
//            for (Material material :
//                    changedMaterialList) {
//                if (editedMaterials.contains(material)){
//                    int materialIndex = editedMaterials.indexOf(material);
//                    if (editedMaterials.get(materialIndex).getPrice() != material.getPrice()){
//                        editedMaterials.set(materialIndex, material);
//                    }
//                    continue;
//                }
//                editedMaterials.add(material);
//                for (Material editedMaterial
//                        : editedMaterials
//                ) {
//                    if MaterialCache.contains(editedMaterial)
//                }
//            }
//        }
//
//        if (changedMaterialList.size() > 0) {
//            for (Material material :
//                    changedMaterialList) {
//                int materialIndex = databaseMaterialsList.indexOf(material);
//                if (databaseMaterialsList.get(materialIndex).getPrice() != material.getPrice()) {
//                    editedMaterials.add(material);
//                }
//            }
//        }

        consoleMaterialsOutLog("MATERIALS EDITED:", editedMaterials);

        if (editedMaterials.size() > 0) {
            for (Material material :
                    editedMaterials) {
                if (MaterialCache.contains(material)) {
                    MaterialCache.update(material);
                }
                databaseRepository.updateMaterial(material);
            }
            listener.onMaterialPriceChanged();
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

    //Getters
    public MaterialTableModel getMaterialTableModel() {
        return materialTableModel;
    }
}
