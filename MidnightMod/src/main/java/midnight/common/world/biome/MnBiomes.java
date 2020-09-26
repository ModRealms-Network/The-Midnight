package midnight.common.world.biome;

import midnight.common.Midnight;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.ForgeRegistry;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.ObjectHolder;

import javax.annotation.Nonnull;

@ObjectHolder("midnight")
public final class MnBiomes {
    public static final Biome NIGHT_PLAINS = inj();
    public static final Biome VIGILANT_FOREST = inj();
    public static final Biome DECEITFUL_BOG = inj();

    public static void registerBiomes(IForgeRegistry<Biome> registry) {
        registry.registerAll(
            biome("night_plains", new NightPlainsBiome()),
            biome("vigilant_forest", new VigilantForestBiome()),
            biome("deceitful_bog", new DeceitfulBogBiome())
        );
    }

    private MnBiomes() {
    }

    private static <B extends Biome> B biome(String id, B biome) {
        biome.setRegistryName(Midnight.resLoc(id));
        return biome;
    }

    public static int id(Biome biome) {
        return ((ForgeRegistry<Biome>) ForgeRegistries.BIOMES).getID(biome);
    }

    public static Biome biome(int id) {
        return ((ForgeRegistry<Biome>) ForgeRegistries.BIOMES).getValue(id);
    }

    @Nonnull
    @SuppressWarnings("ConstantConditions")
    private static Biome inj() {
        return null;
    }
}
