package midnight.client;

import net.minecraftforge.api.distmarker.Dist;

import midnight.client.proxy.ClientBlockItemProxy;
import midnight.common.Midnight;
import midnight.common.proxy.BlockItemProxy;
import midnight.core.util.MnUtil;
import midnight.data.MidnightData;

/**
 * The client-only main class of the Midnight, to handle certain client-only initialization and processing.
 */
public class MidnightClient extends Midnight {
    @Override
    public Dist getRuntimeDist() {
        return Dist.CLIENT;
    }

    /**
     * Returns the direct instance of {@link MidnightClient}, or throws a {@link ClassCastException} when not on the
     * client (that would already have caused a class loading failure in most cases).
     */
    public static MidnightClient get() {
        return (MidnightClient) Midnight.get();
    }

    @Override
    protected BlockItemProxy makeBlockItemProxy() {
        return new ClientBlockItemProxy();
    }

    /**
     * Creates the proper instance of {@link MidnightClient}, using {@link MidnightData} when on data generation mode.
     */
    public static MidnightClient dataOrClient() {
        return MnUtil.callForDatagen(() -> MidnightData::new, () -> MidnightClient::new);
    }
}
