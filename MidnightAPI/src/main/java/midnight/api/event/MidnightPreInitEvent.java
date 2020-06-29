package midnight.api.event;

import net.minecraftforge.api.distmarker.Dist;

import midnight.api.IMidnight;

/**
 * Midnight event triggered at mod construction time, once the Midnight has been loaded. Initialize your plugins with
 * this event. Do not initialize your plugins when <i>your</i> mod constructs, as the Midnight might not be loaded by
 * that time, and it may not load at all...
 */
public class MidnightPreInitEvent extends AbstractMidnightInitEvent {
    public MidnightPreInitEvent(IMidnight midnight, Dist dist) {
        super(midnight, dist);
    }
}
