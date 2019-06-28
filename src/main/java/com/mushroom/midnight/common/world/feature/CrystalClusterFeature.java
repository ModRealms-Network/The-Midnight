package com.mushroom.midnight.common.world.feature;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

import java.util.Random;

public class CrystalClusterFeature extends MidnightAbstractFeature {
    private final int radius;
    private final int maxHeight;

    private final BlockState rock;
    private final BlockState crystal;

    public CrystalClusterFeature(int radius, int maxHeight, BlockState rock, BlockState crystal) {
        this.radius = radius;
        this.maxHeight = maxHeight;
        this.rock = rock;
        this.crystal = crystal;
    }

    @Override
    public boolean placeFeature(World world, Random rand, BlockPos origin) {
        int size = (this.radius * 2) + 1;

        int[] heights = new int[size * size];
        BlockPos basePos = this.populateHeights(world, rand, origin, heights, size);

        if (basePos == null || !this.canGenerate(world, origin, heights, size)) {
            return false;
        }

        BlockPos.MutableBlockPos mutablePos = new BlockPos.MutableBlockPos();
        for (int localZ = -this.radius; localZ <= this.radius; localZ++) {
            for (int localX = -this.radius; localX <= this.radius; localX++) {
                int height = heights[(localX + this.radius) + (localZ + this.radius) * size];
                if (height > 0) {
                    mutablePos.setPos(basePos.getX() + localX, basePos.getY(), basePos.getZ() + localZ);
                    this.generatePillar(world, rand, mutablePos, height);
                }
            }
        }

        return true;
    }

    private BlockPos populateHeights(World world, Random rand, BlockPos origin, int[] heights, int size) {
        BlockPos.MutableBlockPos basePos = new BlockPos.MutableBlockPos(origin);

        for (int localZ = -this.radius; localZ <= this.radius; localZ++) {
            for (int localX = -this.radius; localX <= this.radius; localX++) {
                int index = (localX + this.radius) + (localZ + this.radius) * size;

                double deltaX = localX + rand.nextDouble() * 2.0 - 1.0;
                double deltaZ = localZ + rand.nextDouble() * 2.0 - 1.0;
                double distance = Math.sqrt(deltaX * deltaX + deltaZ * deltaZ);
                double alpha = (this.radius - distance) / this.radius;

                int height = MathHelper.floor(alpha * this.maxHeight);
                if (height > 0) {
                    BlockPos surfacePos = this.findSurfaceBelow(world, origin.add(localX, 0, localZ), 16);
                    if (surfacePos == null) {
                        return null;
                    }

                    if (surfacePos.getY() < basePos.getY()) {
                        basePos.setY(surfacePos.getY());
                    }

                    heights[index] = height;
                }
            }
        }

        return basePos.toImmutable();
    }

    private boolean canGenerate(World world, BlockPos origin, int[] heights, int size) {
        BlockPos.MutableBlockPos mutablePos = new BlockPos.MutableBlockPos(origin);
        int centerHeight = heights[this.radius + this.radius * size] + 1;
        for (int localY = 0; localY < centerHeight; localY++) {
            mutablePos.setY(origin.getY() + localY);
            if (!world.isAirBlock(mutablePos)) {
                return false;
            }
        }
        return true;
    }

    private void generatePillar(World world, Random rand, BlockPos.MutableBlockPos mutablePos, int height) {
        int originY = mutablePos.getY();
        for (int offsetY = 0; offsetY < height; offsetY++) {
            mutablePos.setY(originY + offsetY);
            this.trySetBlock(world, mutablePos, this.rock);
        }
        if (rand.nextInt(2) == 0) {
            mutablePos.setY(originY + height);
            this.trySetBlock(world, mutablePos, this.crystal);
        }
    }

    private BlockPos findSurfaceBelow(World world, BlockPos origin, int maxSteps) {
        BlockState currentState = world.getBlockState(origin);
        BlockPos.MutableBlockPos currentPos = new BlockPos.MutableBlockPos(origin);
        for (int i = 0; i < maxSteps; i++) {
            currentPos.move(Direction.DOWN);
            BlockState nextState = world.getBlockState(currentPos);
            if (currentState.getBlock() == Blocks.AIR && nextState.isSideSolid(world, currentPos, Direction.UP)) {
                currentPos.move(Direction.UP);
                return currentPos.toImmutable();
            }
            currentState = nextState;
        }
        return null;
    }

    private void trySetBlock(World world, BlockPos pos, BlockState state) {
        BlockState currentState = world.getBlockState(pos);
        if (currentState.getBlock().isReplaceable(world, pos)) {
            world.setBlockState(pos, state, 3);
        }
    }
}
