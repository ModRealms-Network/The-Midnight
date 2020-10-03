package midnight.common.world.levelgen.midnight;

import net.minecraft.world.biome.provider.BiomeProvider;

public abstract class MidnightGenerator {
    protected final long seed;
    protected final BiomeProvider biomeProvider;
    protected final MidnightChunkGenerator chunkGenerator;

    public MidnightGenerator(long seed, BiomeProvider biomeProvider, MidnightChunkGenerator chunkGenerator) {
        this.seed = seed;
        this.biomeProvider = biomeProvider;
        this.chunkGenerator = chunkGenerator;
    }
}
