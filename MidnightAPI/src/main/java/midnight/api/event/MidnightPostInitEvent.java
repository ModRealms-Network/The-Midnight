package midnight.api.event;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.event.lifecycle.FMLLoadCompleteEvent;

import midnight.api.IMidnight;

/**
 * Midnight event triggered at load-completion time, once the Midnight has received the {@link FMLLoadCompleteEvent}.
 *
 * @author Shadew
 * @since 0.6.0
 */
public class MidnightPostInitEvent extends AbstractMidnightInitEvent {
    /**
     * @param midnight The {@link IMidnight} instance.
     * @param dist The {@link Dist} to run the post-initialization event.
     */
    public MidnightPostInitEvent(IMidnight midnight, Dist dist) {
        super(midnight, dist);
    }
}
