package com.mushroom.midnight.common.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockDoublePlant;
import net.minecraft.block.IGrowable;
import net.minecraft.block.state.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.Random;
import java.util.function.Supplier;

public class MidnightFungiBlock extends MidnightPlantBlock implements IGrowable {
    private final Supplier<Block> parentSupplier;

    public MidnightFungiBlock(Supplier<Block> parentSupplier) {
        super(true);
        this.parentSupplier = parentSupplier;
    }

    @Override
    protected boolean canSustainBush(BlockState state) {
        return state.isBlockNormalCube();
    }

    @Override
    public boolean canPlaceBlockAt(World world, BlockPos pos) {
        return canSustainBush(world.getBlockState(pos.down())) && world.getBlockState(pos).getBlock().isReplaceable(world, pos);
    }

    @Override
    public boolean canGrow(World worldIn, BlockPos pos, BlockState state, boolean isClient) {
        return true;
    }

    @Override
    public boolean canUseBonemeal(World worldIn, Random rand, BlockPos pos, BlockState state) {
        return true;
    }

    @Override
    public void grow(World world, Random rand, BlockPos pos, BlockState state) {
        if (world.isAirBlock(pos.up())) {
            world.setBlockState(pos, parentSupplier.get().getDefaultState().withProperty(BlockDoublePlant.HALF, BlockDoublePlant.EnumBlockHalf.LOWER), 2);
            world.setBlockState(pos.up(), parentSupplier.get().getDefaultState().withProperty(BlockDoublePlant.HALF, BlockDoublePlant.EnumBlockHalf.UPPER), 2);
        }
    }
}