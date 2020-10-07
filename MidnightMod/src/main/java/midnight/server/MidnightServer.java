package midnight.server;

import net.minecraftforge.api.distmarker.Dist;

import midnight.common.Midnight;

/**
 * The dedicated-server-only main class of the Midnight, just to indicate the dedicated server. Everything just
 * delegates to {@link Midnight} currently.
 *
 * @author Shadew
 * @version 0.6.0
 * @since 0.6.0
 */
public class MidnightServer extends Midnight {
    @Override
    public Dist getRuntimeDist() {
        return Dist.DEDICATED_SERVER;
    }

    /**
     * Returns the direct instance of {@link MidnightServer}, or throws a {@link ClassCastException} when not running on
     * the dedicated server (that would already have caused a class loading failure in most cases).
     */
    public static MidnightServer get() {
        return (MidnightServer) Midnight.get();
    }
}
