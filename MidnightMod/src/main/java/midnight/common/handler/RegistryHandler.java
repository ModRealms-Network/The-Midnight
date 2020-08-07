package midnight.common.handler;

import com.google.common.reflect.Reflection;
import midnight.common.block.MnBlocks;
import midnight.common.item.MnItems;
import midnight.common.registry.RegistryManager;
import midnight.common.world.dimension.MnDimensions;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraftforge.common.ModDimension;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

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
            MnItems.class,
            MnDimensions.class
        );
    }

    @SubscribeEvent
    public static void registerBlocks(RegistryEvent.Register<Block> event) {
        RegistryManager.BLOCKS.fillRegistry(event.getRegistry());
    }

    @SubscribeEvent
    public static void registerDimensions(RegistryEvent.Register<ModDimension> event) {
        RegistryManager.DIMENSIONS.fillRegistry(event.getRegistry());
    }

    @SubscribeEvent
    public static void registerItems(RegistryEvent.Register<Item> event) {
        RegistryManager.ITEMS.fillRegistry(event.getRegistry());
    }
}
