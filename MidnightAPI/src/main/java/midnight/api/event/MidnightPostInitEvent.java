package midnight.api.event;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.event.lifecycle.FMLLoadCompleteEvent;

import midnight.api.IMidnight;

/**
 * Midnight event triggered at load-completion time, once the Midnight has received the {@link FMLLoadCompleteEvent}.
 */
public class MidnightPostInitEvent extends AbstractMidnightInitEvent {
    public MidnightPostInitEvent(IMidnight midnight, Dist dist) {
        super(midnight, dist);
    }
}
