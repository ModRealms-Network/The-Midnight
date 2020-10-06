package midnight.common.sound;

import midnight.common.Midnight;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.registries.IForgeRegistry;

import java.util.ArrayList;
import java.util.List;

/**
 * Registers and stores the list of Midnight sound events.
 *
 * @author Shadew
 */
public final class MnSoundEvents {
    private static final List<SoundEvent> SOUNDS = new ArrayList<>();

    public static final SoundEvent BLOCK_MUD_BREAK = make("block.mud.break");
    public static final SoundEvent BLOCK_MUD_STEP = make("block.mud.step");

    public static void registerSoundEvents(IForgeRegistry<SoundEvent> registry) {
        SOUNDS.forEach(registry::register);
    }

    private MnSoundEvents() {
    }

    /**
     * Grabs the sounds from a group in sounds.json to make into a SoundEvent.
     *
     * @param type The sound group from sounds.json to use.
     * @return The SoundEvent to be used in game.
     */
    private static SoundEvent make(String type) {
        ResourceLocation id = Midnight.resLoc(type);
        SoundEvent event = new SoundEvent(id).setRegistryName(id);
        SOUNDS.add(event);
        return event;
    }
}
