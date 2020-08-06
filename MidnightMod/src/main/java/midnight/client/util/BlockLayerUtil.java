package midnight.client.util;

import midnight.client.proxy.ClientBlockItemProxy;
import midnight.core.util.BlockLayer;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraft.fluid.Fluid;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Predicate;

/**
 * Manages block render layers before block registry has been set up. The block layers will be stored in this class
 * until {@link #register()} is called. After calling {@link #register()}, this class will delegate the registry of
 * block layers directly to {@link RenderTypeLookup}.
 *
 * Since the {@link ClientBlockItemProxy} will always receive the render layer of a block <b>before</b> the block has
 * been registered, the {@link RenderTypeLookup} will not use the correct registry delegate to register the render layer
 * and so it will not work. To ensure the delegate is available, block render layers will be managed by this class until
 * this render layers are ready to be registered.
 */
public final class BlockLayerUtil {
    private static boolean registered;
    private static final HashMap<Block, Predicate<BlockLayer>> BLOCK_LAYERS = new HashMap<>();
    private static final HashMap<Fluid, Predicate<BlockLayer>> FLUID_LAYERS = new HashMap<>();

    private BlockLayerUtil() {
    }

    /**
     * Flush all cached render layers to the {@link RenderTypeLookup} and ensure that render types registered after the
     * call to this method will be delegated to the lookup directly.
     */
    public static void register() {
        if(registered) return;
        registered = true; // Mark that we have called register() - from now on delegate directly to RenderTypeLookup

        for(Map.Entry<Block, Predicate<BlockLayer>> entry : BLOCK_LAYERS.entrySet()) {
            RenderTypeLookup.setRenderLayer(entry.getKey(), type -> entry.getValue().test(BlockLayer.getFromRenderType(type)));
        }
        for(Map.Entry<Fluid, Predicate<BlockLayer>> entry : FLUID_LAYERS.entrySet()) {
            RenderTypeLookup.setRenderLayer(entry.getKey(), type -> entry.getValue().test(BlockLayer.getFromRenderType(type)));
        }
    }

    // Self explanatory methods - maybe add javadoc later...

    public static void setBlockLayer(Block block, Predicate<BlockLayer> predicate) {
        if(registered) {
            RenderTypeLookup.setRenderLayer(block, type -> predicate.test(BlockLayer.getFromRenderType(type)));
            return;
        }
        BLOCK_LAYERS.put(block, predicate);
    }

    public static void setBlockLayer(Block block, BlockLayer layer) {
        setBlockLayer(block, l -> l == layer);
    }

    public static void setFluidLayer(Fluid fluid, Predicate<BlockLayer> predicate) {
        if(registered) {
            RenderTypeLookup.setRenderLayer(fluid, type -> predicate.test(BlockLayer.getFromRenderType(type)));
            return;
        }
        FLUID_LAYERS.put(fluid, predicate);
    }

    public static void setFluidLayer(Fluid fluid, BlockLayer layer) {
        setFluidLayer(fluid, l -> l == layer);
    }
}
