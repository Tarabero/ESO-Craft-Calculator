package util;

import entities.Material;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;

public class MaterialCache {

    private static final HashMap<Integer, Material> materialsCache = new HashMap<>();
    private static MaterialCache materialCacheInstance = null;

    public MaterialCache() {
    }

    public static MaterialCache getInstance() {
        if (materialCacheInstance == null) {
            materialCacheInstance = new MaterialCache();
        }
        return materialCacheInstance;
    }

    public void add(Material material) {
        materialsCache.put(material.getId(), material);
    }

    public boolean contains(Material material) {
        return materialsCache.containsKey(material.getId());
    }

    @Nullable
    public Material get(int materialId) {
        return materialsCache.get(materialId);
    }

    public void update(Material material) {
        Material cachedMaterial = materialsCache.get(material.getId());
        if (cachedMaterial != null) {
            cachedMaterial.setPrice(material.getPrice());
        }
    }
}
