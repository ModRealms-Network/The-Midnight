package midnight.common.world.levelgen.midnight;

import net.minecraft.world.IWorld;
import net.minecraft.world.biome.provider.BiomeProvider;

public abstract class MidnightGenerator {
    protected final IWorld world;
    protected final BiomeProvider biomeProvider;
    protected final MidnightChunkGenerator chunkGenerator;

    public MidnightGenerator(IWorld world, BiomeProvider biomeProvider, MidnightChunkGenerator chunkGenerator) {
        this.world = world;
        this.biomeProvider = biomeProvider;
        this.chunkGenerator = chunkGenerator;
    }
}
