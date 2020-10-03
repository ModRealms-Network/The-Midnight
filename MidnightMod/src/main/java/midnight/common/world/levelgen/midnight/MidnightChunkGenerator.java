package midnight.common.world.levelgen.midnight;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.biome.provider.BiomeProvider;
import net.minecraft.world.chunk.IChunk;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.Heightmap;
import net.minecraft.world.gen.WorldGenRegion;
import net.minecraft.world.gen.feature.structure.StructureManager;
import net.minecraft.world.gen.settings.DimensionStructuresSettings;

public class MidnightChunkGenerator extends ChunkGenerator {
    public static final Codec<MidnightChunkGenerator> CODEC = RecordCodecBuilder.create(
        instance -> instance.group(
            Codec.LONG.fieldOf("seed")
                      .stable()
                      .forGetter(gen -> gen.seed),
            BiomeProvider.CODEC.fieldOf("biome_source")
                               .forGetter(gen -> gen.biomeProvider),
            DimensionStructuresSettings.CODEC.fieldOf("structurees")
                                             .forGetter(gen -> gen.structuresSettings)
        ).apply(instance, instance.stable(MidnightChunkGenerator::new)));

    private final long seed;
    private final DimensionStructuresSettings structuresSettings;
    private final MidnightTerrainGenerator terrainGen;
    private final MidnightSurfaceGenerator surfaceGen;
    private final MidnightBedrockGenerator bedrockGen;

    public MidnightChunkGenerator(long seed, BiomeProvider biomes, DimensionStructuresSettings settings) {
        super(biomes, settings);
        this.seed = seed;
        this.structuresSettings = settings;

        terrainGen = new MidnightTerrainGenerator(seed, biomes, this, 72);
        surfaceGen = new MidnightSurfaceGenerator(seed, biomes, this);
        bedrockGen = new MidnightBedrockGenerator(seed, biomes, this);
    }

    @Override
    protected Codec<? extends ChunkGenerator> getCodec() {
        return CODEC;
    }

    @Override
    public ChunkGenerator withSeed(long seed) {
        return new MidnightChunkGenerator(seed, biomeProvider, structuresSettings);
    }

    @Override
    public void buildSurface(WorldGenRegion world, IChunk chunk) {
        surfaceGen.generateSurface(world, chunk);
        bedrockGen.generateBedrock(chunk);
    }

    @Override
    public int getGroundHeight() {
        return 72;
    }

    @Override
    public void populateNoise(IWorld world, StructureManager structureManager, IChunk chunk) {
        terrainGen.generateTerrain(world, chunk);
    }

    public int getHeight(int x, int z, Heightmap.Type type) {
        return 100; // TODO We may no longer have world
    }

    @Override
    public int getSeaLevel() {
        return 72;
    }

    @Override // Once again nobody seems to care about the name of this function...
    public int func_222529_a(int x, int z, Heightmap.Type type) {
        return getHeight(x, z, type);
    }

    @Override
    public IBlockReader getColumnSample(int x, int z) {
        return null; // TODO Fine to be null but for datapack support this is mandatory some day (otherwise NPEs occur)
    }
}
