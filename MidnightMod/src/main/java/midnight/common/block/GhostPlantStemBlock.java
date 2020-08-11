package midnight.common.block;

import net.minecraft.block.BlockState;
import net.minecraft.block.RotatedPillarBlock;
import net.minecraft.util.Direction;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class GhostPlantStemBlock extends RotatedPillarBlock {
    public GhostPlantStemBlock(Properties properties) {
        super(properties);
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    @SuppressWarnings("deprecation")
    public boolean isSideInvisible(BlockState state, BlockState neighbor, Direction side) {
        return neighbor.getBlock() == this || super.isSideInvisible(state, neighbor, side);
    }
}
