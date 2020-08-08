package midnight.client.handler;

import midnight.client.MidnightClient;
import net.minecraft.client.world.ClientWorld;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.world.WorldEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = "midnight", value = Dist.CLIENT)
public final class BiomeColorCacheHandler {
    private BiomeColorCacheHandler() {
    }

    @SubscribeEvent
    public static void onWorldLoad(WorldEvent.Load load) {
        if(load.getWorld() instanceof ClientWorld) {
            MidnightClient.get().getDarkWaterColorCache().reset();
        }
    }
}
