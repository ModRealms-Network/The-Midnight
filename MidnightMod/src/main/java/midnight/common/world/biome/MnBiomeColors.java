package midnight.common.world.biome;

import midnight.api.biome.IMidnightBiome;
import net.minecraft.world.level.ColorResolver;

public final class MnBiomeColors {
    public static final ColorResolver NIGHT_GRASS = (biome, x, z) -> IMidnightBiome.get(biome).getMidnightGrassColor(x, z);
    public static final ColorResolver DARK_WATER = (biome, x, z) -> IMidnightBiome.get(biome).getMidnightWaterColor(x, z);
    public static final ColorResolver SHADOWROOT = (biome, x, z) -> IMidnightBiome.get(biome).getShadowrootColor(x, z);

    private MnBiomeColors() {
    }
}
