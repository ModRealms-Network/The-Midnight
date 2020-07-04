package midnight.common.handler;

import com.google.common.reflect.Reflection;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import midnight.common.block.MnBlocks;
import midnight.common.item.MnItems;
import midnight.common.registry.RegistryManager;

/**
 * Event handler responsible for handling all registry events and passing them to their respective object holders.
 */
@Mod.EventBusSubscriber(modid = "midnight", bus = Mod.EventBusSubscriber.Bus.MOD)
public final class RegistryHandler {
    private RegistryHandler() {
    }

    static {
        Reflection.initialize(
                MnBlocks.class,
                MnItems.class
        );
    }

    @SubscribeEvent
    public static void registerBlocks(RegistryEvent.Register<Block> event) {
        RegistryManager.BLOCKS.fillRegistry(event.getRegistry());
    }

    @SubscribeEvent
    public static void registerItems(RegistryEvent.Register<Item> event) {
        RegistryManager.ITEMS.fillRegistry(event.getRegistry());
    }
}
