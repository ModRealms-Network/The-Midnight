package midnight.core.dimension;

import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.DimensionSettings;

@FunctionalInterface
public interface IChunkGenFactory {
    ChunkGenerator createChunkGenerator(Registry<Biome> biomes, Registry<DimensionSettings> configs, long seed);
}
