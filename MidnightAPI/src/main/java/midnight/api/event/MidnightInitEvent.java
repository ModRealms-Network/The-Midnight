package midnight.api.event;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

import midnight.api.IMidnight;

/**
 * Midnight event triggered at initialization time, once the Midnight has received the {@link FMLCommonSetupEvent}.
 *
 * @author Shadew
 * @since 0.6.0
 */
public class MidnightInitEvent extends AbstractMidnightInitEvent {
    /**
     * @param midnight The {@link IMidnight} instance.
     * @param dist The {@link Dist} to run the initialization event.
     */
    public MidnightInitEvent(IMidnight midnight, Dist dist) {
        super(midnight, dist);
    }
}
