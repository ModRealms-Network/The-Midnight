package midnight.common.sound;

import midnight.common.Midnight;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.registries.IForgeRegistry;

import java.util.ArrayList;
import java.util.List;

public final class MnSoundEvents {
    private static final List<SoundEvent> SOUNDS = new ArrayList<>();

    public static final SoundEvent BLOCK_MUD_BREAK = make("block.mud.break");
    public static final SoundEvent BLOCK_MUD_STEP = make("block.mud.step");

    public static void registerSoundEvents(IForgeRegistry<SoundEvent> registry) {
        SOUNDS.forEach(registry::register);
    }

    private MnSoundEvents() {
    }

    private static SoundEvent make(String type) {
        ResourceLocation id = Midnight.resLoc(type);
        SoundEvent event = new SoundEvent(id).setRegistryName(id);
        SOUNDS.add(event);
        return event;
    }
}
