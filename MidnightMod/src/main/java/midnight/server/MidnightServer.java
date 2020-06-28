package midnight.server;

import net.minecraftforge.api.distmarker.Dist;

import midnight.common.Midnight;

public class MidnightServer extends Midnight {
    @Override
    public Dist getRuntimeDist() {
        return Dist.DEDICATED_SERVER;
    }

    public static MidnightServer get() {
        return (MidnightServer) Midnight.get();
    }
}
