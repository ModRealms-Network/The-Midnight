package midnight.common.world.levelgen;

import midnight.common.block.MnBlocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.minecraft.world.biome.provider.BiomeProvider;
import net.minecraft.world.chunk.IChunk;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.GenerationSettings;
import net.minecraft.world.gen.Heightmap;
import net.minecraft.world.gen.WorldGenRegion;

public class MidnightChunkGenerator extends ChunkGenerator<GenerationSettings> {
    public MidnightChunkGenerator(IWorld world, BiomeProvider biomes, GenerationSettings settings) {
        super(world, biomes, settings);
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
        BlockPos.Mutable mpos = new BlockPos.Mutable();
        for(int y = 0; y < 72; y++) {
            for(int x = 0; x < 16; x++) {
                for(int z = 0; z < 16; z++) {
                    mpos.setPos(x, y, z);
                    chunk.setBlockState(mpos, MnBlocks.NIGHT_STONE.getDefaultState(), false);
                }
            }
        }
    }

    @Override
    public int getHeight(int x, int z, Heightmap.Type type) {
        return world.getHeight(type, x, z);
    }
}
