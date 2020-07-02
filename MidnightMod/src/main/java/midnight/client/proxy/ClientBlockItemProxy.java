package midnight.client.proxy;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderTypeLookup;
import midnight.common.proxy.BlockItemProxy;
import midnight.core.util.BlockLayer;

import java.util.function.Predicate;

public class ClientBlockItemProxy extends BlockItemProxy {
    @Override
    public void registerRenderLayer(Block block, BlockLayer renderType) {
        RenderTypeLookup.setRenderLayer(block, renderType.getRenderType());
    }

    @Override
    public void registerRenderLayer(Block block, Predicate<BlockLayer> renderType) {
        RenderTypeLookup.setRenderLayer(block, type -> renderType.test(BlockLayer.getFromRenderType(type)));
    }
}
