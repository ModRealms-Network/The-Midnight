package midnight.common.block;

import net.minecraft.block.BlockState;
import net.minecraft.block.BushBlock;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;

public class MnPlantBlock extends BushBlock {
    private VoxelShape hitbox = VoxelShapes.fullCube();
    private OffsetType offsetType = OffsetType.NONE;

    protected MnPlantBlock(Properties props) {
        super(props);
    }

    public void setHitbox(VoxelShape hitbox) {
        this.hitbox = hitbox;
    }

    public void setPlantHitbox(double size, double height) {
        double radius = size / 2;
        setHitbox(makeCuboidShape(8 - radius, 0, 8 - radius, 8 + radius, height, 8 + radius));
    }

    public void setOffsetType(OffsetType offsetType) {
        this.offsetType = offsetType;
    }

    @Override
    @SuppressWarnings("deprecation")
    public VoxelShape getShape(BlockState state, IBlockReader world, BlockPos pos, ISelectionContext context) {
        return hitbox;
    }

    @Override
    @SuppressWarnings("deprecation")
    public VoxelShape getCollisionShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        return VoxelShapes.empty();
    }

    @Override
    protected boolean isValidGround(BlockState state, IBlockReader world, BlockPos pos) {
        return state.getBlock() instanceof NightDirtBlock || super.isValidGround(state, world, pos);
    }

    @Override
    public OffsetType getOffsetType() {
        return offsetType;
    }
}
