package midnight.common.handler;

import midnight.common.block.MnBlocks;
import midnight.common.block.fluid.MnFluids;
import midnight.common.sound.MnSoundEvents;
import midnight.common.world.biome.MnBiomes;
import midnight.common.world.levelgen.surface.MnSurfaceBuilders;
import net.minecraft.block.Block;
import net.minecraft.fluid.Fluid;
import net.minecraft.item.Item;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilder;
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

    @SubscribeEvent
    public static void registerBlocks(RegistryEvent.Register<Block> event) {
        MnBlocks.registerBlocks(event.getRegistry());
    }

    // TODO Dimensions
//    @SubscribeEvent
//    public static void registerDimensions(RegistryEvent.Register<ModDimension> event) {
//        MnDimensions.registerDimensions(event.getRegistry());
//    }

    @SubscribeEvent
    public static void registerItems(RegistryEvent.Register<Item> event) {
        MnBlocks.registerItems(event.getRegistry());
    }

    @SubscribeEvent
    public static void registerFluids(RegistryEvent.Register<Fluid> event) {
        MnFluids.registerFluids(event.getRegistry());
    }

    @SubscribeEvent
    public static void registerSoundEvents(RegistryEvent.Register<SoundEvent> event) {
        MnSoundEvents.registerSoundEvents(event.getRegistry());
    }

    @SubscribeEvent
    public static void registerBiomes(RegistryEvent.Register<Biome> event) {
        MnBiomes.registerBiomes(event.getRegistry());
    }

    @SubscribeEvent
    public static void registerSurfaceBuilders(RegistryEvent.Register<SurfaceBuilder<?>> event) {
        MnSurfaceBuilders.registerSurfaceBuilders(event.getRegistry());
    }
}
