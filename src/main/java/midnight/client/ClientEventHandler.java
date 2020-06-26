package midnight.client;

import net.minecraft.client.entity.player.ClientPlayerEntity;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.ClientPlayerNetworkEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod.EventBusSubscriber( Dist.CLIENT )
public final class ClientEventHandler {
    private static final Logger LOGGER = LogManager.getLogger( "The Midnight" );

    private ClientEventHandler() {
    }

    /*
     * This method is fired whenever LoggedInEvent is called.
     * It is currently used to display the warning message to the player when logging into a world.
     * The message is also sent to the console at WARN level.
     */
    @SubscribeEvent
    public static void loggedInEvent( ClientPlayerNetworkEvent.LoggedInEvent event ) {
        ClientPlayerEntity player = event.getPlayer();

        if( player != null ) {
            LOGGER.warn( "The Midnight for Minecraft 1.16.1 is still under active development. " +
                             "Many features will be missing and/or completely unusable. You have been warned!" );
            event.getPlayer().sendMessage( new TranslationTextComponent( "The Midnight for Minecraft 1.16.1 is still under active development. " +
                                                                             "Many features will be missing and/or completely unusable. You have been warned!" ).func_240699_a_( TextFormatting.RED ), event.getPlayer().getUniqueID() );
        }
    }
}
