package midnight.client.handler;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.ClientPlayerNetworkEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import net.minecraft.client.entity.player.ClientPlayerEntity;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import midnight.MidnightMod;

/*
 * TODO: Make this the overall event handler or have multiple event handler classes?
 */
@Mod.EventBusSubscriber(Dist.CLIENT)
public final class ClientEventHandler {
    private static final Logger LOGGER = LogManager.getLogger("Midnight Mod");

    private ClientEventHandler() {
    }

    /*
     * This method is fired whenever LoggedInEvent is called.
     * It is currently used to display the warning message to the player when logging into a world.
     * The message is also sent to the console at WARN level.
     */
    @SubscribeEvent
    public static void loggedInEvent(ClientPlayerNetworkEvent.LoggedInEvent event) {
        ClientPlayerEntity player = event.getPlayer();

        // Null-check the player
        if (player != null) {
            LOGGER.warn(MidnightMod.DEV_WARNING);
            event.getPlayer().sendMessage(new TranslationTextComponent(MidnightMod.DEV_WARNING).func_240699_a_(TextFormatting.RED), event.getPlayer().getUniqueID());
        }
    }
}
