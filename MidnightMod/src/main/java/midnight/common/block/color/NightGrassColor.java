package midnight.common.block.color;

import net.minecraft.block.BlockState;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ILightReader;

import javax.annotation.Nullable;

public class NightGrassColor implements IColoredBlock {
    @Override
    public int getColor(BlockState state, @Nullable ILightReader world, @Nullable BlockPos pos, int tintIndex) {
        return 0x8C74A1;
    }

    @Override
    public int getColor(ItemStack stack, int tintIndex) {
        return 0x8C74A1;
    }
}
