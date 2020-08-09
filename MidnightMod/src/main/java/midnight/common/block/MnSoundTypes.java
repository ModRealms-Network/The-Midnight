package midnight.common.block;

import midnight.common.sound.MnSoundEvents;
import net.minecraft.block.SoundType;

public final class MnSoundTypes {
    public static final SoundType MUD = new SoundType(
        1, 1,
        MnSoundEvents.BLOCK_MUD_BREAK,
        MnSoundEvents.BLOCK_MUD_STEP,
        MnSoundEvents.BLOCK_MUD_BREAK,
        MnSoundEvents.BLOCK_MUD_STEP,
        MnSoundEvents.BLOCK_MUD_STEP
    );

    private MnSoundTypes() {
    }
}
