package midnight.common.world.levelgen.midnight;

import net.minecraft.world.IWorld;
import net.minecraft.world.biome.provider.BiomeProvider;
import net.minecraft.world.chunk.IChunk;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.GenerationSettings;
import net.minecraft.world.gen.Heightmap;
import net.minecraft.world.gen.WorldGenRegion;

public class MidnightChunkGenerator extends ChunkGenerator<GenerationSettings> {

    private final MidnightTerrainGenerator terrainGen;

    public MidnightChunkGenerator(IWorld world, BiomeProvider biomes, GenerationSettings settings) {
        super(world, biomes, settings);
        terrainGen = new MidnightTerrainGenerator(world, biomes, this, 72);
    }

    @Override
    public void generateSurface(WorldGenRegion region, IChunk chunk) {

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
}
