package midnight.common.world.biome;

import midnight.common.registry.RegistryManager;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.ForgeRegistry;
import net.minecraftforge.registries.ObjectHolder;

@ObjectHolder("midnight")
public final class MnBiomes {
    public static final Biome NIGHT_PLAINS = register("night_plains", new NightPlainsBiome());
    public static final Biome VIGILANT_FOREST = register("vigilant_forest", new VigilantForestBiome());
    public static final Biome DECEITFUL_BOG = register("deceitful_bog", new DeceitfulBogBiome());

    private MnBiomes() {
    }

    private static <B extends Biome> B register(String id, B biome) {
        RegistryManager.BIOMES.register(id, biome);
        return biome;
    }

    public static int id(Biome biome) {
        return ((ForgeRegistry<Biome>) ForgeRegistries.BIOMES).getID(biome);
    }

    public static Biome biome(int id) {
        return ((ForgeRegistry<Biome>) ForgeRegistries.BIOMES).getValue(id);
    }
}
