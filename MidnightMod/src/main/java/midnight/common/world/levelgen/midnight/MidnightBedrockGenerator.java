package midnight.common.world.levelgen.midnight;

import midnight.common.block.MnBlocks;
import net.minecraft.util.SharedSeedRandom;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.biome.provider.BiomeProvider;
import net.minecraft.world.chunk.IChunk;

public class MidnightBedrockGenerator extends MidnightGenerator {
    public MidnightBedrockGenerator(long seed, BiomeProvider biomeProvider, MidnightChunkGenerator chunkGenerator) {
        super(seed, biomeProvider, chunkGenerator);
    }

    public void generateBedrock(IChunk chunk) {
        SharedSeedRandom ssr = new SharedSeedRandom(17705921372L);
        ssr.setBaseChunkSeed(chunk.getPos().x, chunk.getPos().z);

        BlockPos.Mutable mpos = new BlockPos.Mutable();

        for(int y = 0; y < 5; y++) {
            for(int x = 0; x < 16; x++) {
                for(int z = 0; z < 16; z++) {
                    mpos.setPos(x, y, z);

                    if(ssr.nextInt(5) < 5 - y) {
                        chunk.setBlockState(mpos, MnBlocks.NIGHT_BEDROCK.getDefaultState(), false);
                    }
                }
            }
        }
    }
}
