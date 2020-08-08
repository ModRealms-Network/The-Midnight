package midnight.core.biome;

import com.google.common.collect.Maps;
import midnight.api.biome.IMidnightBiome;
import net.minecraft.entity.EntityClassification;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.carver.ConfiguredCarver;
import net.minecraft.world.gen.carver.ICarverConfig;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.IFeatureConfig;
import net.minecraft.world.gen.feature.structure.Structure;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public abstract class MidnightBiome extends Biome implements IMidnightBiome {
    private static final Logger LOGGER = LogManager.getLogger("MidnightBiome");

    private int midnightGrassColor = 0x8F54A1;
    private int midnightWaterColor = 0x2C0266;


    protected final Map<GenerationStage.Carving, List<ConfiguredCarver<?>>> mnCarvers = Maps.newEnumMap(GenerationStage.Carving.class);
    protected final Map<GenerationStage.Decoration, List<ConfiguredFeature<?, ?>>> mnFeatures = Maps.newEnumMap(GenerationStage.Decoration.class);
    protected final Map<Structure<?>, IFeatureConfig> mnStructures = Maps.newHashMap();
    protected final Map<EntityClassification, List<SpawnListEntry>> mnSpawns = Maps.newHashMap();

    protected MidnightBiome(Builder builder) {
        super(builder);
    }

    public void setMidnightGrassColor(int midnightGrassColor) {
        this.midnightGrassColor = midnightGrassColor;
    }

    public void setMidnightWaterColor(int midnightWaterColor) {
        this.midnightWaterColor = midnightWaterColor;
    }

    @Override
    public int getMidnightGrassColor(double x, double z) {
        return midnightGrassColor;
    }

    @Override
    public int getMidnightWaterColor(double x, double z) {
        return midnightWaterColor;
    }

    @Override
    public void addFeature(GenerationStage.Decoration stage, ConfiguredFeature<?, ?> feature) {
        LOGGER.warn("Mod tries to modify Midnight biome without the API. If you wanna modify a Midnight biome, use the Midnight API.");
    }

    @Override
    public void addSpawn(EntityClassification type, SpawnListEntry entry) {
        LOGGER.warn("Mod tries to modify Midnight biome without the API. If you wanna modify a Midnight biome, use the Midnight API.");
    }

    @Override
    public <C extends ICarverConfig> void addCarver(GenerationStage.Carving stage, ConfiguredCarver<C> carver) {
        LOGGER.warn("Mod tries to modify Midnight biome without the API. If you wanna modify a Midnight biome, use the Midnight API.");
    }

    @Override
    public <C extends IFeatureConfig> void addStructure(ConfiguredFeature<C, ? extends Structure<C>> structure) {
        LOGGER.warn("Mod tries to modify Midnight biome without the API. If you wanna modify a Midnight biome, use the Midnight API.");
    }

    @Override
    public void addMidnightFeature(GenerationStage.Decoration stage, ConfiguredFeature<?, ?> feature) {
        mnFeatures.computeIfAbsent(stage, key -> new ArrayList<>()).add(feature);
    }

    @Override
    public <C extends ICarverConfig> void addMidnightCarver(GenerationStage.Carving stage, ConfiguredCarver<C> carver) {
        mnCarvers.computeIfAbsent(stage, key -> new ArrayList<>()).add(carver);
    }

    @Override
    public <C extends IFeatureConfig> void addMidnightStructure(ConfiguredFeature<C, ? extends Structure<C>> structure) {
        mnStructures.put(structure.feature, structure.config);
    }

    @Override
    public void addMidnightSpawn(EntityClassification type, SpawnListEntry entry) {
        mnSpawns.computeIfAbsent(type, key -> new ArrayList<>()).add(entry);
    }

    @Override
    public List<ConfiguredFeature<?, ?>> getFeatures(GenerationStage.Decoration decorationStage) {
        return Collections.unmodifiableList(mnFeatures.computeIfAbsent(decorationStage, key -> new ArrayList<>()));
    }

    @Override
    public List<ConfiguredCarver<?>> getCarvers(GenerationStage.Carving stage) {
        return Collections.unmodifiableList(mnCarvers.computeIfAbsent(stage, key -> new ArrayList<>()));
    }

    @Override
    public List<SpawnListEntry> getSpawns(EntityClassification creatureType) {
        return Collections.unmodifiableList(mnSpawns.computeIfAbsent(creatureType, key -> new ArrayList<>()));
    }

    @Nullable
    @Override
    @SuppressWarnings("unchecked")
    public <C extends IFeatureConfig> C getStructureConfig(Structure<C> structure) {
        return (C) mnStructures.get(structure);
    }
}
