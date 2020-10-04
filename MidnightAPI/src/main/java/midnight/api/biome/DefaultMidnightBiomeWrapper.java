package midnight.api.biome;

import net.minecraft.world.biome.Biome;

import java.util.HashMap;

/**
 * Default wrapper for vanilla biomes and other biomes not implementing the {@link IMidnightBiome} interface, to be
 * returned by {@link IMidnightBiome#get}.
 */
// TODO Delete this?
class DefaultMidnightBiomeWrapper implements IMidnightBiome {
    // Cache instances to reduce overloads of GC operations.
    private static final HashMap<Biome, DefaultMidnightBiomeWrapper> CACHE = new HashMap<>();

    private final Biome biome;

    private DefaultMidnightBiomeWrapper(Biome biome) {
        this.biome = biome;
    }

    @Override
    public Biome getBiome() {
        return biome;
    }

    @Override
    public int getMidnightGrassColor(double x, double z) {
        return 0x506C78;
    }

    @Override
    public int getMidnightWaterColor(double x, double z) {
        return 0xFFFFFF;
    }

    @Override
    public double getTerrainHeight() {
        return 2;
    }

    @Override
    public double getTerrainDifference() {
        return 2;
    }

    @Override
    public double getTerrainHilliness() {
        return 1;
    }

    @Override
    public double getTerrainGranularity() {
        return 0.5;
    }

    static DefaultMidnightBiomeWrapper get(Biome biome) {
        return CACHE.computeIfAbsent(biome, DefaultMidnightBiomeWrapper::new);
    }
}
