package midnight.common.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;

import javax.annotation.Nullable;

public class HangingLeavesBlock extends HangingVinesBlock {
    public static final BooleanProperty END = BooleanProperty.create("end");
    public static final BooleanProperty ROOT = BooleanProperty.create("root");

    public HangingLeavesBlock(Properties properties) {
        super(properties);
        setDefaultState(
            getStateContainer().getBaseState()
                               .with(ROOT, true)
                               .with(END, false)
        );
    }

    @Override
    public BlockState updatePostPlacement(BlockState state, Direction dir, BlockState adjState, IWorld world, BlockPos pos, BlockPos adjPos) {
        if(dir.getAxis() == Direction.Axis.Y)
            state = getAppropriateState(world, pos);
        return super.updatePostPlacement(state, dir, adjState, world, pos, adjPos);
    }

    @Override
    @Nullable
    public BlockState getStateForPlacement(BlockItemUseContext ctx) {
        return getAppropriateState(ctx.getWorld(), ctx.getPos());
    }

    private BlockState getAppropriateState(IWorld world, BlockPos pos) {
        BlockState aboveState = world.getBlockState(pos.up());
        BlockState belowState = world.getBlockState(pos.down());
        return getDefaultState()
                   .with(END, belowState.getBlock() != this)
                   .with(ROOT, aboveState.getBlock() != this);
    }

    @Override
    public boolean isValidGround(BlockState state, IBlockReader world, BlockPos pos) {
        if(state.getBlock() == this) {
            return state.get(ROOT);
        }
        return super.isValidGround(state, world, pos);
    }

    @Override
    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(END, ROOT);
    }
}
