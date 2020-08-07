package midnight.client.proxy;

import midnight.client.MidnightClient;
import midnight.common.block.color.IColoredBlock;
import midnight.common.item.IColoredItem;
import midnight.common.proxy.BlockItemProxy;
import midnight.core.util.BlockLayer;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraft.client.renderer.color.BlockColors;
import net.minecraft.client.renderer.color.ItemColors;
import net.minecraft.fluid.Fluid;
import net.minecraft.item.Item;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Predicate;

/**
 * The client side of the {@link BlockItemProxy}. This manages the registry of render types to the {@link
 * RenderTypeLookup} and colors to the {@link BlockColors} and {@link ItemColors}.
 *
 * This class will cache all registered colors/render types until {@link #init()} has been called to ensure we can
 * register at all times.
 */
public class ClientBlockItemProxy extends BlockItemProxy {
    private static boolean registered;
    private static final HashMap<Block, Predicate<BlockLayer>> BLOCK_LAYERS = new HashMap<>();
    private static final HashMap<Fluid, Predicate<BlockLayer>> FLUID_LAYERS = new HashMap<>();
    private static final HashMap<Block, IColoredBlock> BLOCK_COLORS = new HashMap<>();
    private static final HashMap<Item, IColoredItem> ITEM_COLORS = new HashMap<>();

    /**
     * Register all cached colors/render types. Called from {@link MidnightClient#init()}.
     */
    @Override
    public void init() {
        if(registered) return;
        registered = true; // Mark that we have called init() - from now on delegate directly to RenderTypeLookup

        for(Map.Entry<Block, Predicate<BlockLayer>> entry : BLOCK_LAYERS.entrySet()) {
            RenderTypeLookup.setRenderLayer(entry.getKey(), type -> entry.getValue().test(BlockLayer.getFromRenderType(type)));
        }
        for(Map.Entry<Fluid, Predicate<BlockLayer>> entry : FLUID_LAYERS.entrySet()) {
            RenderTypeLookup.setRenderLayer(entry.getKey(), type -> entry.getValue().test(BlockLayer.getFromRenderType(type)));
        }
        for(Map.Entry<Block, IColoredBlock> entry : BLOCK_COLORS.entrySet()) {
            Minecraft.getInstance().getBlockColors().register(entry.getValue()::getColor, entry.getKey());
        }
        for(Map.Entry<Item, IColoredItem> entry : ITEM_COLORS.entrySet()) {
            Minecraft.getInstance().getItemColors().register(entry.getValue()::getColor, entry.getKey());
        }
    }

    @Override
    public void registerRenderLayer(Block block, BlockLayer layer) {
        registerRenderLayer(block, l -> l == layer);
    }

    @Override
    public void registerRenderLayer(Block block, Predicate<BlockLayer> predicate) {
        if(registered) {
            RenderTypeLookup.setRenderLayer(block, type -> predicate.test(BlockLayer.getFromRenderType(type)));
            return;
        }
        BLOCK_LAYERS.put(block, predicate);
    }

    @Override
    public void registerRenderLayer(Fluid fluid, BlockLayer layer) {
        registerRenderLayer(fluid, l -> l == layer);
    }

    @Override
    public void registerRenderLayer(Fluid fluid, Predicate<BlockLayer> predicate) {
        if(registered) {
            RenderTypeLookup.setRenderLayer(fluid, type -> predicate.test(BlockLayer.getFromRenderType(type)));
            return;
        }
        FLUID_LAYERS.put(fluid, predicate);
    }

    @Override
    public void registerColoredItem(Item item, IColoredItem color) {
        if(registered) {
            Minecraft.getInstance().getItemColors().register(color::getColor, item);
            return;
        }
        ITEM_COLORS.put(item, color);
    }

    @Override
    public void registerColoredBlock(Block block, IColoredBlock color) {
        if(registered) {
            Minecraft.getInstance().getBlockColors().register(color::getColor, block);
            return;
        }
        BLOCK_COLORS.put(block, color);
    }
}
