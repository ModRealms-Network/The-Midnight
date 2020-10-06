package midnight.client;

import midnight.client.environment.MidnightEnvironmentRenderer;
import midnight.client.util.BiomeColorCache;
import midnight.common.Midnight;
import midnight.common.block.MnBlocks;
import midnight.common.block.fluid.MnFluids;
import midnight.core.util.MnUtil;
import midnight.data.MidnightData;
import net.minecraftforge.api.distmarker.Dist;

/**
 * The client-only main class of the Midnight, to handle certain client-only initialization and processing.
 *
 * @author Shadew
 * @since 0.6.0
 */
public class MidnightClient extends Midnight {
    private final BiomeColorCache darkWaterColorCache = new BiomeColorCache();
    private final BiomeColorCache nightGrassColorCache = new BiomeColorCache();

    @Override
    public void init() {
        MnBlocks.setupRenderers();
        MnFluids.setupRenderers();

        MidnightEnvironmentRenderer.init();
    }

    @Override
    public Dist getRuntimeDist() {
        return Dist.CLIENT;
    }

    public BiomeColorCache getDarkWaterColorCache() {
        return darkWaterColorCache;
    }

    public BiomeColorCache getNightGrassColorCache() {
        return nightGrassColorCache;
    }

    /**
     * Returns the direct instance of {@link MidnightClient}, or throws a {@link ClassCastException} when not on the
     * client (that would already have caused a class loading failure in most cases).
     */
    public static MidnightClient get() {
        return (MidnightClient) Midnight.get();
    }

    /**
     * Creates the proper instance of {@link MidnightClient} by using {@link MidnightData}
     * when on data generation mode, and returns it
     */
    public static MidnightClient dataOrClient() {
        return MnUtil.callForDatagen(() -> MidnightData::new, () -> MidnightClient::new);
    }
}
