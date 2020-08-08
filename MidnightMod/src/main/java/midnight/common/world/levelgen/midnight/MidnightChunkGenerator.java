package midnight.common.world.levelgen.midnight;

import midnight.common.block.MnBlocks;
import net.minecraft.world.IWorld;
import net.minecraft.world.biome.provider.BiomeProvider;
import net.minecraft.world.chunk.IChunk;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.GenerationSettings;
import net.minecraft.world.gen.Heightmap;
import net.minecraft.world.gen.WorldGenRegion;

public class MidnightChunkGenerator extends ChunkGenerator<GenerationSettings> {

    private final MidnightTerrainGenerator terrainGen;
    private final MidnightSurfaceGenerator surfaceGen;

    public MidnightChunkGenerator(IWorld world, BiomeProvider biomes) {
        super(world, biomes, createSettings());
        terrainGen = new MidnightTerrainGenerator(world, biomes, this, 72);
        surfaceGen = new MidnightSurfaceGenerator(world, biomes, this);
    }

    @Override
    public void generateSurface(WorldGenRegion region, IChunk chunk) {
        surfaceGen.generateSurface(region, chunk);
    }

    @Override
    public int getGroundHeight() {
        return 72;
    }

    @Override
    public void makeBase(IWorld world, IChunk chunk) {
        terrainGen.generateTerrain(world, chunk);
    }

    @Override
    public int getHeight(int x, int z, Heightmap.Type type) {
        return world.getHeight(type, x, z);
    }

    @Override
    public int getSeaLevel() {
        return 72;
    }

    private static GenerationSettings createSettings() {
        GenerationSettings out = new GenerationSettings();
        out.setDefaultBlock(MnBlocks.NIGHT_STONE.getDefaultState());
        out.setDefaultFluid(MnBlocks.DARK_WATER.getDefaultState());
        return out;
    }
}
