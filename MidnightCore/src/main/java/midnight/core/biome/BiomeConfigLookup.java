package midnight.core.biome;

import net.minecraft.util.ResourceLocation;
import net.minecraft.world.biome.Biome;

import java.util.HashMap;
import java.util.Map;

public final class BiomeConfigLookup {
    private static final Map<ResourceLocation, BiomeColoring> COLORING_MAP = new HashMap<>();
    private static final Map<ResourceLocation, TerrainFactors> TERRAIN_FACTORS_MAP = new HashMap<>();

    private BiomeConfigLookup() {
    }

    public static void putColoring(ResourceLocation id, BiomeColoring colors) {
        COLORING_MAP.put(id, colors);
    }

    public static void putTerrainFactors(ResourceLocation id, TerrainFactors terrainFactors) {
        TERRAIN_FACTORS_MAP.put(id, terrainFactors);
    }

    public static BiomeColoring getColoring(Biome biome) {
        return COLORING_MAP.computeIfAbsent(biome.getRegistryName(), k -> new BiomeColoring.Builder().build());
    }

    public static TerrainFactors getTerrainFactors(Biome biome) {
        return TERRAIN_FACTORS_MAP.computeIfAbsent(biome.getRegistryName(), k -> new TerrainFactors.Builder().build());
    }
}
