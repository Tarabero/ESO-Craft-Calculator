package util;

import entities.Material;

import java.util.LinkedList;

public class MaterialCache {

    private static final LinkedList<Material> materialCacheList = new LinkedList<>();

    public MaterialCache() {

    }

    public static void add(Material material) {
        materialCacheList.add(material);
    }

    public static boolean contains(Material material) {
        return materialCacheList.contains(material);
    }

    public static Material get(int materialId) {
        for (Material material :
                materialCacheList) {
            if (material.getId() == materialId) {
                return material;
            }
        }
        return null;
    }

    public static void update(Material material) {
        int materialIndex = materialCacheList.indexOf(material);
        materialCacheList.set(materialIndex, material);
    }
}
