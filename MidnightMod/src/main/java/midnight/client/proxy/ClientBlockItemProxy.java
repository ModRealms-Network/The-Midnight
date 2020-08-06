package midnight.client.proxy;

import midnight.client.util.BlockLayerUtil;
import midnight.common.proxy.BlockItemProxy;
import midnight.core.util.BlockLayer;
import net.minecraft.block.Block;

import java.util.function.Predicate;

public class ClientBlockItemProxy extends BlockItemProxy {
    @Override
    public void registerRenderLayer(Block block, BlockLayer layer) {
        BlockLayerUtil.setBlockLayer(block, layer);
    }

    @Override
    public void registerRenderLayer(Block block, Predicate<BlockLayer> predicate) {
        BlockLayerUtil.setBlockLayer(block, predicate);
    }
}
