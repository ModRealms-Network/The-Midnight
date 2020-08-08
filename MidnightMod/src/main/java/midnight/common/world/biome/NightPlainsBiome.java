package midnight.common.world.biome;

import midnight.common.block.MnBlocks;
import midnight.core.biome.MidnightBiome;
import net.minecraft.world.gen.surfacebuilders.DefaultSurfaceBuilder;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilderConfig;

public class NightPlainsBiome extends MidnightBiome {
    protected NightPlainsBiome() {
        super(
            new Builder().depth(0.1f)
                         .scale(0.1f)
                         .downfall(0)
                         .category(Category.MUSHROOM)
                         .waterColor(0x5280EB)
                         .waterFogColor(0x395087)
                         .precipitation(RainType.NONE)
                         .temperature(1)
                         .surfaceBuilder(
                             new DefaultSurfaceBuilder(SurfaceBuilderConfig::deserialize),
                             new SurfaceBuilderConfig(
                                 MnBlocks.NIGHT_GRASS_BLOCK.getDefaultState(),
                                 MnBlocks.NIGHT_DIRT.getDefaultState(),
                                 MnBlocks.NIGHT_DIRT.getDefaultState()
                             )
                         )
        );

        setMidnightGrassColor(0x8F54A1);
        setMidnightWaterColor(0x2C0266);

        setTerrainHeight(2);
        setTerrainDifference(2);
        setTerrainHilliness(1);
        setTerrainGranularity(0.4);
    }
}
