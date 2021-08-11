package util;

import db.dao.interfaces.MaterialDao;
import entitites.*;

import java.util.ArrayList;
import java.util.List;

import static entitites.MaterialType.*;

public class QualityResourceCollector {
    private final static int FLAG_NULL_INTERRUPT = -1;

    private final MaterialDao materialDao;
    private final MaterialType[] jewelryTypes = {FINE_JEWELRY, RARE_JEWELRY, EPIC_JEWELRY, LEGENDARY_JEWELRY};
    private final MaterialType[] clothingTypes = {FINE_CLOTHING, RARE_CLOTHING, EPIC_CLOTHING, LEGENDARY_CLOTHING};
    private final MaterialType[] smithingTypes = {FINE_SMITHING, RARE_SMITHING, EPIC_SMITHING, LEGENDARY_SMITHING};
    private final MaterialType[] woodworkTypes = {FINE_WOODWORK, RARE_WOODWORK, EPIC_WOODWORK, LEGENDARY_WOODWORK};


    private final int[] commonMaterialRequired = {2, 3, 4, 8};
    private final int[] jewelryMaterialRequired = {1, 2, 3, 4};


    public QualityResourceCollector(MaterialDao materialDao) {
        this.materialDao = materialDao;
    }

    public List<CraftResource> getResourcesFor(QualityType qualityType, Workbench workbench) {
        List<CraftResource> resourceList = new ArrayList<>();
        if (isCommon(qualityType)){
            return resourceList;
        }
        resourceList.addAll(createCraftResourceList(qualityType, workbench));
        return resourceList;
    }

    private boolean isCommon(QualityType type){
        return type == QualityType.COMMON;
    }

    private List<CraftResource> createCraftResourceList(QualityType qualityType, Workbench workbench){
        List<CraftResource> resourceList = new ArrayList<>();
        List<Material> materials = getMaterialsFor(workbench);
        int[] materialsRequired = getMaterialsRequiredArray(workbench);
        int qualityTypeIndex = getIndexOf(qualityType);
        if (materialsRequired.length >= qualityTypeIndex && materials.size() >= qualityTypeIndex) {
            for (int i = 0; i <= qualityTypeIndex; i++) {
                CraftResource craftResource = new CraftResource(materials.get(i), materialsRequired[i]);
                resourceList.add(craftResource);
            }
        }
        return resourceList;
    }

    private List<Material> getMaterialsFor(Workbench workbench) {
        List<Material> materials = new ArrayList<>();
        MaterialType[] materialTypes = getMaterialTypesFor(workbench);
        for (MaterialType type : materialTypes) {
            materials.add(materialDao.getMaterialFor(type));
        }
        return materials;
    }

    private MaterialType[] getMaterialTypesFor(Workbench workbench) {
        if (workbench == null){
            return new MaterialType[]{};
        }
        switch (workbench) {
            case CLOTHING:
                return clothingTypes;
            case SMITHING:
                return smithingTypes;
            case WOODWORKING:
                return woodworkTypes;
            case JEWELRY:
                return jewelryTypes;
            default:
                return new MaterialType[]{};
        }
    }

    private int[] getMaterialsRequiredArray(Workbench workbench) {
        if (isJewelry(workbench)) {
            return jewelryMaterialRequired;
        }
        return commonMaterialRequired;
    }

    private boolean isJewelry(Workbench workbench){
        return workbench == Workbench.JEWELRY;
    }

    private int getIndexOf(QualityType type) {
        QualityType[] qualityTypes = QualityType.values();
        for (int i = 1; i < qualityTypes.length; i++) {
            if (qualityTypes[i] == type) {
                return i - 1;
            }
        }
        return FLAG_NULL_INTERRUPT;
    }
}
