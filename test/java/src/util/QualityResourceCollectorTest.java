package util;

import db.DatabaseHelper;
import db.dao.MaterialDaoImpl;
import db.dao.interfaces.MaterialDao;
import db.entities.MaterialType;
import item.CraftResource;
import item.QualityType;
import item.Workbench;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static db.entities.MaterialType.*;
import static java.util.Arrays.asList;
import static java.util.Collections.singletonList;
import static org.junit.Assert.*;

public class QualityResourceCollectorTest {

    private final List<MaterialType> jewelryTypes = asList(FINE_JEWELRY, RARE_JEWELRY, EPIC_JEWELRY, LEGENDARY_JEWELRY);
    private final List<MaterialType> woodworkTypes = asList(FINE_WOODWORK, RARE_WOODWORK, EPIC_WOODWORK, LEGENDARY_WOODWORK);

    private final List<Integer> commonMaterialRequired = asList(2, 3, 4, 8);
    private final List<Integer> jewelryMaterialRequired = asList(1, 2, 3, 4);

    private QualityResourceCollector qualityResourceCollector;
    private DatabaseHelper databaseHelper;

    @Before
    public void setup(){
        databaseHelper = DatabaseHelper.getInstance();
        databaseHelper.connect();
        MaterialDao materialDao = new MaterialDaoImpl(databaseHelper);
        qualityResourceCollector = new QualityResourceCollector(materialDao);
    }

    @After
    public void tearDown(){
        databaseHelper.close();
    }

    @Test
    public void when_QualityTypeNull_WorkbenchNull_ReturnEmptyList(){
        List<CraftResource> craftResources = qualityResourceCollector.getResourcesFor(null, null);
        checkEmpty(craftResources);
    }

    @Test
    public void when_QualityTypeNull_WorkbenchNotNull_ReturnEmptyList(){
        List<CraftResource> craftResources = qualityResourceCollector.getResourcesFor(null, Workbench.JEWELRY);
        checkEmpty(craftResources);
    }

    @Test
    public void when_QualityTypeNotNull_WorkbenchNull_ReturnEmptyList(){
        List<CraftResource> craftResources = qualityResourceCollector.getResourcesFor(QualityType.LEGENDARY, null);
        checkEmpty(craftResources);
    }

    @Test
    public void when_QualityTypeCommon_WorkbenchNotNull_ReturnEmptyList(){
        List<CraftResource> craftResources = qualityResourceCollector.getResourcesFor(QualityType.COMMON, Workbench.JEWELRY);
        checkEmpty(craftResources);
    }

    @Test
    public void when_QualityTypeLegendary_WorkbenchNotNull_ReturnListMeetsExpected(){
        List<CraftResource> craftResources = qualityResourceCollector.getResourcesFor(QualityType.LEGENDARY, Workbench.WOODWORKING);
        checkQuantity(craftResources, commonMaterialRequired);
        checkMaterialTypes(craftResources, woodworkTypes);
    }

    @Test
    public void when_QualityTypeFine_WorkbenchNotNull_ReturnListMeetsExpected(){
        List<CraftResource> craftResources = qualityResourceCollector.getResourcesFor(QualityType.FINE, Workbench.WOODWORKING);
        checkQuantity(craftResources, singletonList(2));
        checkMaterialTypes(craftResources, singletonList(FINE_WOODWORK));
    }

    @Test
    public void when_QualityTypeRare_WorkbenchNotNull_ReturnListMeetsExpected(){
        List<CraftResource> craftResources = qualityResourceCollector.getResourcesFor(QualityType.RARE, Workbench.WOODWORKING);
        checkQuantity(craftResources, asList(2, 3));
        checkMaterialTypes(craftResources, asList(FINE_WOODWORK, RARE_WOODWORK));
    }

    @Test
    public void when_QualityTypeEpic_WorkbenchNotNull_ReturnListMeetsExpected(){
        List<CraftResource> craftResources = qualityResourceCollector.getResourcesFor(QualityType.EPIC, Workbench.WOODWORKING);
        checkQuantity(craftResources, asList(2, 3, 4));
        checkMaterialTypes(craftResources, asList(FINE_WOODWORK, RARE_WOODWORK, EPIC_WOODWORK));
    }

    @Test
    public void when_QualityTypeLegendary_WorkbenchJewelry_ReturnListMeetsExpected(){
        List<CraftResource> craftResources = qualityResourceCollector.getResourcesFor(QualityType.LEGENDARY, Workbench.JEWELRY);
        checkQuantity(craftResources, jewelryMaterialRequired);
        checkMaterialTypes(craftResources, jewelryTypes);
    }

    private void checkQuantity(List<CraftResource> actualResults, List<Integer> expectedResourceQuantity){
        assertNotNull(actualResults);
        assertEquals(actualResults.size(), expectedResourceQuantity.size());
        for (int i = 0; i < actualResults.size(); i++){
            assertEquals(actualResults.get(i).getQuantity(), expectedResourceQuantity.get(i).intValue());
        }
    }

    private void checkMaterialTypes(List<CraftResource> actualResults, List<MaterialType> expectedMaterialTypes){
        assertNotNull(actualResults);
        assertEquals(actualResults.size(), expectedMaterialTypes.size());
        for (int i = 0; i < actualResults.size(); i++){
            assertEquals(actualResults.get(i).getMaterial().getMaterialType(),
                    expectedMaterialTypes.get(i));
        }
    }

    private void checkEmpty(List<CraftResource> actualResults){
        assertNotNull(actualResults);
        assertTrue(actualResults.isEmpty());
    }
}