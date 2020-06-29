package midnight.api.event;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

import midnight.api.IMidnight;

/**
 * Midnight event triggered at initialization time, once the Midnight has received the {@link FMLCommonSetupEvent}.
 */
public class MidnightInitEvent extends AbstractMidnightInitEvent {
    public MidnightInitEvent(IMidnight midnight, Dist dist) {
        super(midnight, dist);
    }
}
