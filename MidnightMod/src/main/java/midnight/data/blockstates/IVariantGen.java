package midnight.data.blockstates;

import com.google.gson.JsonElement;

import net.minecraft.block.BlockState;

@FunctionalInterface
public interface IVariantGen {
    JsonElement makeVariant(BlockState state);
}
