package midnight.common.world.biome;

import midnight.common.registry.RegistryManager;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.registries.ObjectHolder;

@ObjectHolder("midnight")
public final class MnBiomes {
    public static final Biome NIGHT_PLAINS = register("night_plains", new NightPlainsBiome());

    private MnBiomes() {
    }

    private static <B extends Biome> B register(String id, B biome) {
        RegistryManager.BIOMES.register(id, biome);
        return biome;
    }
}
