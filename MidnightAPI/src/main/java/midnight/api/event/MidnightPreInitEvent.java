package midnight.api.event;

import net.minecraftforge.api.distmarker.Dist;

import midnight.api.IMidnight;

/**
 * Midnight event triggered at mod construction time, once the Midnight has been loaded. Initialize your plugins with
 * this event. Do not initialize your plugins when <i>your</i> mod constructs, as the Midnight might not be loaded by
 * that time, and it may not load at all...
 *
 * @author Shadew
 * @since 0.6.0
 */
public class MidnightPreInitEvent extends AbstractMidnightInitEvent {
    /**
     * @param midnight The {@link IMidnight} instance.
     * @param dist The {@link Dist} to run the pre-initialization event.
     */
    public MidnightPreInitEvent(IMidnight midnight, Dist dist) {
        super(midnight, dist);
    }
}
