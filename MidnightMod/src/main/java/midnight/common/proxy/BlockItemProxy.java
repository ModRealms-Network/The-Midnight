package midnight.common.proxy;

import net.minecraft.block.Block;
import midnight.core.util.BlockLayer;

import java.util.function.Predicate;

public class BlockItemProxy {
    public void registerRenderLayer(Block block, BlockLayer renderType) {
        // Client-only implementation
    }

    public void registerRenderLayer(Block block, Predicate<BlockLayer> renderType) {
        // Client-only implementation
    }
}
