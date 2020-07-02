package midnight.data.blockstates;

import com.google.gson.JsonElement;

import net.minecraft.block.Block;
import net.minecraft.util.ResourceLocation;

@FunctionalInterface
public interface IBlockstateGen {
    JsonElement makeJson(ResourceLocation id, Block block);
}
