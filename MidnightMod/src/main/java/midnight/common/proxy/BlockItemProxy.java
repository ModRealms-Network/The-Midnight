package midnight.common.proxy;

import midnight.common.block.color.IColoredBlock;
import midnight.common.item.IColoredItem;
import midnight.core.util.BlockLayer;
import net.minecraft.block.Block;
import net.minecraft.fluid.Fluid;
import net.minecraft.item.Item;

import java.util.function.Predicate;

public class BlockItemProxy {
    public void registerRenderLayer(Block block, BlockLayer renderType) {
        // Client-only implementation
    }

    public void registerRenderLayer(Block block, Predicate<BlockLayer> renderType) {
        // Client-only implementation
    }

    public void registerRenderLayer(Fluid fluid, BlockLayer renderType) {
        // Client-only implementation
    }

    public void registerRenderLayer(Fluid fluid, Predicate<BlockLayer> renderType) {
        // Client-only implementation
    }

    public void registerColoredItem(Item item, IColoredItem color) {
        // Client-only implementation
    }

    public void registerColoredBlock(Block block, IColoredBlock color) {
        // Client-only implementation
    }

    public void init() {
    }
}
