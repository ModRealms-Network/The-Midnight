package midnight.core.biome;

import midnight.api.biome.IMidnightBiome;

public interface IMidnightBiomeBase extends IMidnightBiome {
    void setColoring(BiomeColoring coloring);
    void setTerrainFactors(TerrainFactors factors);
}
