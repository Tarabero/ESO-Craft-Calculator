package gui.dialog.priceeditor;

import entities.Material;
import util.DatabaseRepository;
import util.MaterialCache;
import util.TtcParser;

import javax.swing.*;
import java.io.FileNotFoundException;
import java.util.List;

public class PriceEditorDialogPresenter {
    private static final String LUA_FILE_EXTENSION = ".lua";
    private static final String MATERIALS_IMPORTED_DIALOG_TITLE = "Price import successful";
    private static final String MATERIALS_IMPORTED_DIALOG_MESSAGE = "%1$s of %2$s materials updated successfully!";
    private static final String FILE_ERROR_DIALOG_TITLE = "File error";
    private static final String FILE_ERROR_DIALOG_MESSAGE = "Chosen file is not valid!";

    public interface FileErrorListener {
        void onError(String errorTitle, String errorMessage);
    }

    private FileErrorListener errorListener;
    private final DatabaseRepository databaseRepository;
    private MaterialTableModel materialTableModel;
    private final List<Material> listMaterials;

    public PriceEditorDialogPresenter(final DatabaseRepository databaseRepository, FileErrorListener errorListener) {
        this.databaseRepository = databaseRepository;
        this.errorListener = errorListener;
        listMaterials = getAllMaterials();
        setupMaterialTableModel();
    }

    public void setupMaterialTableModel() {
        materialTableModel = new MaterialTableModel(listMaterials);
    }

    public void updateTableWithTtcPrices(String ttcPriceListPath) throws FileNotFoundException {
        if (ttcPriceListPath.endsWith(LUA_FILE_EXTENSION)) {
            parseTtcFile(ttcPriceListPath);
        } else fireError(FILE_ERROR_DIALOG_MESSAGE);
    }

    private void parseTtcFile(String ttcPriceListPath) throws FileNotFoundException {
        TtcParser parser = new TtcParser(ttcPriceListPath);
        if (parser.isTtcPriceTable()) {
            updateMaterialsPricesWith(parser);
        } else fireError(FILE_ERROR_DIALOG_MESSAGE);
    }

    private void updateMaterialsPricesWith(TtcParser parser) {
        int materialsQuantity = listMaterials.size();
        int successfullyEditedQuantity = 0;
        for (Material material :
                listMaterials) {
            int ttcID = material.getTtcId();
            int newPrice = (int) Math.round(parser.getSuggestedPriceFromTtcFor(ttcID));
            if (newPrice == TtcParser.PARSE_ERROR) {
                continue;
            }
            material.setPrice(newPrice);
            successfullyEditedQuantity++;
        }
        materialTableModel.updateMaterials(listMaterials);
        showMaterialsImportedDialog(successfullyEditedQuantity, materialsQuantity);
        parser = null; //cleaning instance
    }

    private void showMaterialsImportedDialog(int successfullyEdited, int overallQuantity) {
        JOptionPane.showMessageDialog(null,
                String.format(MATERIALS_IMPORTED_DIALOG_MESSAGE, successfullyEdited, overallQuantity),
                MATERIALS_IMPORTED_DIALOG_TITLE,
                JOptionPane.PLAIN_MESSAGE);
    }

    private void fireError(String errorMessage) {
        if (errorListener != null) {
            errorListener.onError(FILE_ERROR_DIALOG_TITLE, errorMessage);
        }
    }

    private List<Material> getAllMaterials() {
        return databaseRepository.getAllMaterials();
    }

    public void updateMaterialsPrice() {
        List<Material> editedMaterials = materialTableModel.getChangedMaterialPriceList();
        updateMaterialsData(editedMaterials);
    }

    private void updateMaterialsData(List<Material> editedMaterials) {
        for (Material material :
                editedMaterials) {
            MaterialCache materialCache = MaterialCache.getInstance();
            if (materialCache.contains(material)) {
                materialCache.update(material);
            }
            databaseRepository.updateMaterial(material);
        }
    }

    public boolean hasPriceChanged() {
        return !materialTableModel.getChangedMaterialPriceList().isEmpty();
    }

    public MaterialTableModel getMaterialTableModel() {
        return materialTableModel;
    }
}
