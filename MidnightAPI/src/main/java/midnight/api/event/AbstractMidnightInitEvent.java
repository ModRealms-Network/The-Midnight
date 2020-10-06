package midnight.api.event;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.Event;

import midnight.api.IMidnight;

/**
 * The generic event to stay up-to-date with the loading lifecycle of the Midnight. Since plugins inject directly into
 * the Midnight, they should be triggered from the Midnight's loading thread, and not from that of your own mod.
 *
 * @author Shadew
 * @since 0.6.0
 */
public abstract class AbstractMidnightInitEvent extends Event {
    private final IMidnight midnight;
    private final Dist dist;

    protected AbstractMidnightInitEvent(IMidnight midnight, Dist dist) {
        this.midnight = midnight;
        this.dist = dist;
    }

    /**
     * Returns the {@link IMidnight} instance.
     */
    public IMidnight getMidnight() {
        return midnight;
    }

    /**
     * Returns the runtime distribution ({@link Dist}) of the Midnight.
     */
    public Dist getRuntimeDist() {
        return dist;
    }
}
