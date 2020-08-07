package midnight.common.block.color;

import midnight.common.item.IColoredItem;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ILightReader;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nullable;

public interface IColoredBlock extends IColoredItem {
    @OnlyIn(Dist.CLIENT)
    int getColor(BlockState state, @Nullable ILightReader world, @Nullable BlockPos pos, int tintIndex);
}
