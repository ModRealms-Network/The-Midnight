package midnight.client;

import net.minecraftforge.api.distmarker.Dist;

import midnight.client.proxy.ClientBlockItemProxy;
import midnight.common.Midnight;
import midnight.common.proxy.BlockItemProxy;
import midnight.core.util.MnUtil;
import midnight.data.MidnightData;

public class MidnightClient extends Midnight {
    @Override
    public Dist getRuntimeDist() {
        return Dist.CLIENT;
    }

    public static MidnightClient get() {
        return (MidnightClient) Midnight.get();
    }

    @Override
    protected BlockItemProxy makeBlockItemProxy() {
        return new ClientBlockItemProxy();
    }

    public static MidnightClient dataOrClient() {
        return MnUtil.callForDatagen(() -> MidnightData::new, () -> MidnightClient::new);
    }
}
