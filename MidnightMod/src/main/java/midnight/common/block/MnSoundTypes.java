package midnight.common.block;

import midnight.common.sound.MnSoundEvents;
import net.minecraft.block.SoundType;

public final class MnSoundTypes {
    /*
     * Here are the parameters for adding new SoundTypes.
     * 1. Volume (default: 1.0F)
     * 2. Pitch (default: 1.0F)
     * 3. Break Sound
     * 4. Step Sound
     * 5. Place Sound
     * 6. Hit Sound
     * 7. Fall Sound
     */

    public static final SoundType MUD = new SoundType(
        1.0F, 1.0F,
        MnSoundEvents.BLOCK_MUD_BREAK,
        MnSoundEvents.BLOCK_MUD_STEP,
        MnSoundEvents.BLOCK_MUD_BREAK,
        MnSoundEvents.BLOCK_MUD_STEP,
        MnSoundEvents.BLOCK_MUD_STEP
    );

    private MnSoundTypes() {
    }
}
