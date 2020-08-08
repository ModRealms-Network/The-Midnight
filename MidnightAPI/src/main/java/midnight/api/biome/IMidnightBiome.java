package midnight.api.biome;

import net.minecraft.entity.EntityClassification;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.carver.ConfiguredCarver;
import net.minecraft.world.gen.carver.ICarverConfig;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.IFeatureConfig;
import net.minecraft.world.gen.feature.structure.Structure;

/**
 * Marks a biome set to have special interactions with the Midnight, such as specific coloring of Night Grass and Dark
 * Water. All builtin biomes of the Midnight implement this interface. To add or modify a Midnight biome, use this
 * interface.
 * <p>
 * Notable is that builtin Midnight biomes dignore any Features, Carvers, Structures and Spawns that were added via the
 * default biome methods, in order to prevent features etc. from mods that modify all registered biomes to their
 * consent. If a mod needs to modify Midnight biomes specifically, the Midnight biomes should be modified via this
 * interface and not via the biome class itself. For example, to add a feature to a Midnight biome, one should use
 * {@link IMidnightBiome#addMidnightFeature} instead of {@link Biome#addFeature}.
 * </p>
 * <p>
 * Implementing this interface on a class that does not extend {@link Biome} may cause unexpected behaviour.
 * </p>
 */
public interface IMidnightBiome {

    /**
     * Returns the {@link Biome} that implemented this interface.
     */
    default Biome getBiome() {
        return (Biome) this;
    }

    /**
     * Returns the Night Grass color for this biome at a specific position. For most biomes this color is independent of
     * the position but a single biome may use noise to alter the color for a specific location.
     */
    int getMidnightGrassColor(double x, double z);

    /**
     * Returns the Dark Water color for this biome at a specific position. For most biomes this color is independent of
     * the position but a single biome may use noise to alter the color for a specific location.
     */
    int getMidnightWaterColor(double x, double z);

    /**
     * Returns the base terrain height of this biome, as an offset from the water level. This height factor is scaled by
     * 4, so if this returns 1 the terrain is raised by 4 blocks.
     */
    double getTerrainHeight();

    /**
     * Returns the noise interpolation range of this biome - the difference. This range factor is scaled by 4, so if
     * this returns 1 the terrain differs 4 blocks in height.
     */
    double getTerrainDifference();

    /**
     * Returns the variation in the base terrain height of this biome - the hilliness. This hilliness factor is scaled
     * by 4, so if this returns 1 the base height differs by 4 blocks.
     */
    double getTerrainHilliness();

    /**
     * Returns the granularity of the terrain, determining how cliffy and eroded the terrain looks. Lower values make a
     * smoother terrain, while higher values make a more granulated terrain.
     */
    double getTerrainGranularity();

    /**
     * Add a working Feature to this Midnight biome. This replaces {@link Biome#addFeature} in order to prevent
     * batch-modifications on builtin Midnight biomes. Default implementation just delegates to the biome itself, but if
     * you add another biome to the Midnight, it is recommended to re-implement this so other mods won't be able to
     * batch-modify your biome.
     *
     * @param stage   The decoration stage to add the feature for.
     * @param feature The feature, configured.
     */
    default void addMidnightFeature(GenerationStage.Decoration stage, ConfiguredFeature<?, ?> feature) {
        getBiome().addFeature(stage, feature);
    }

    /**
     * Add a working Carver to this Midnight biome. This replaces {@link Biome#addCarver} in order to prevent
     * batch-modifications on builtin Midnight biomes. Default implementation just delegates to the biome itself, but if
     * you add another biome to the Midnight, it is recommended to re-implement this so other mods won't be able to
     * batch-modify you biome.
     *
     * @param stage  The carving stage to add the carver for.
     * @param carver The carver, configured.
     */
    default <C extends ICarverConfig> void addMidnightCarver(GenerationStage.Carving stage, ConfiguredCarver<C> carver) {
        getBiome().addCarver(stage, carver);
    }

    /**
     * Add a working Structure to this Midnight biome. This replaces {@link Biome#addStructure} in order to prevent
     * batch-modifications on builtin Midnight biomes. Default implementation just delegates to the biome itself, but if
     * you add another biome to the Midnight, it is recommended to re-implement this so other mods won't be able to
     * batc-modify your biome.
     *
     * @param structure The structure feature, configured.
     */
    default <C extends IFeatureConfig> void addMidnightStructure(ConfiguredFeature<C, ? extends Structure<C>> structure) {
        getBiome().addStructure(structure);
    }

    /**
     * Add a working Spawn to this Midnight biome. The default {@code addSpawn} method is protected, but to allow other
     * mods to add spawns to Midnight biomes, this method is exposed. Default delegates to the biome itself by getting
     * the list of spawns and adding to that list - if your biome doesn't support, make sure you override this method.
     *
     * @param type  The entity classification to add the spawn for
     * @param entry The spawn list entry
     */
    default void addMidnightSpawn(EntityClassification type, Biome.SpawnListEntry entry) {
        getBiome().getSpawns(type).add(entry); // We can't use 'addSpawn' since that is protected but this works
    }

    /**
     * Get the {@link IMidnightBiome} for a specific biome. This will always return a valid {@link IMidnightBiome}
     * instance, even for biomes that do not implement that interface - if there is no implementation present, a default
     * implementation wrapper will be returned. This means that you <b>CANNOT</b> cast the result of this method to
     * {@link Biome} directly (it may return a wrapper) - use {@link #getBiome} for that instead.
     */
    static IMidnightBiome get(Biome biome) {
        if(biome instanceof IMidnightBiome) {
            return (IMidnightBiome) biome;
        }
        return DefaultMidnightBiomeWrapper.get(biome);
    }
}
