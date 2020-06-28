package midnight.client;

import net.minecraftforge.api.distmarker.Dist;

import midnight.common.Midnight;

public class MidnightClient extends Midnight {
    @Override
    public Dist getRuntimeDist() {
        return Dist.CLIENT;
    }

    public static MidnightClient get() {
        return (MidnightClient) Midnight.get();
    }
}
