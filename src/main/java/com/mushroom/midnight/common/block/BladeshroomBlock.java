package com.mushroom.midnight.common.block;

import com.mushroom.midnight.common.config.MidnightConfig;
import com.mushroom.midnight.common.registry.MidnightItems;
import com.mushroom.midnight.common.util.MidnightDamageSource;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.IGrowable;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.pathfinding.PathNodeType;
import net.minecraft.state.EnumProperty;
import net.minecraft.util.DamageSource;
import net.minecraft.util.Direction;
import net.minecraft.util.EnumHand;
import net.minecraft.util.Hand;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.items.ItemHandlerHelper;

import javax.annotation.Nullable;
import java.util.Locale;
import java.util.Random;

public class BladeshroomBlock extends MidnightPlantBlock implements IGrowable {
    public static final EnumProperty<Stage> STAGE = EnumProperty.create("stage", Stage.class);
    private static final int REGROW_CHANCE = 10;

    private static final AxisAlignedBB BOUNDS = new AxisAlignedBB(0.0625, 0.0, 0.0625, 0.9375, 0.5625, 0.9375);
    private static final AxisAlignedBB STEM_BOUNDS = new AxisAlignedBB(0.25, 0.0, 0.25, 0.75, 0.5, 0.75);

    private static final DamageSource BLADESHROOM_DAMAGE = new MidnightDamageSource("bladeshroom").setDamageBypassesArmor().setDamageIsAbsolute();

    public BladeshroomBlock() {
        super(false);
        this.setDefaultState(this.stateContainer.getBaseState().with(STAGE, Stage.SPORE));
        this.setTickRandomly(true);
        this.setCreativeTab(null);
    }

    @Nullable
    @Override
    public PathNodeType getAiPathNodeType(BlockState state, IBlockReader world, BlockPos pos, @Nullable MobEntity entity) {
        return state.get(STAGE) == Stage.CAPPED ? PathNodeType.DAMAGE_CACTUS : super.getAiPathNodeType(state, world, pos, entity);
    }

    @Override
    protected boolean canSustainBush(BlockState state) {
        return state.isBlockNormalCube();
    }

    @Override
    public boolean onBlockActivated(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockRayTraceResult rayTraceResult) {
        if (state.get(STAGE) == Stage.CAPPED) {
            ItemHandlerHelper.giveItemToPlayer(player, new ItemStack(MidnightItems.BLADESHROOM_CAP));
            world.setBlockState(pos, state.with(STAGE, Stage.STEM));
            if (MidnightConfig.general.bladeshroomDamageChance != 0 && world.rand.nextInt(100) < MidnightConfig.general.bladeshroomDamageChance) {
                player.attackEntityFrom(BLADESHROOM_DAMAGE, 1.0F);
            }
            return true;
        }

        return false;
    }

    @Override
    public void getDrops(NonNullList<ItemStack> drops, IBlockAccess world, BlockPos pos, BlockState state, int fortune) {
        if (state.get(STAGE) == Stage.CAPPED) {
            drops.add(new ItemStack(MidnightItems.BLADESHROOM_CAP));
        }

        Random random = world instanceof World ? ((World) world).rand : RANDOM;
        drops.add(new ItemStack(MidnightItems.BLADESHROOM_SPORES, this.quantityDropped(state, fortune, random)));
    }

    @Override
    public int quantityDropped(BlockState state, int fortune, Random random) {
        int quantity = 0;
        if (state.get(STAGE) == Stage.CAPPED) {
            quantity += 1;
        }
        if (random.nextInt(3) == 0) {
            quantity += 1;
        }
        return quantity;
    }

    @Override
    public AxisAlignedBB getBoundingBox(BlockState state, IBlockAccess source, BlockPos pos) {
        return state.get(STAGE) == Stage.CAPPED ? BOUNDS : STEM_BOUNDS;
    }

    @Override
    public Block.OffsetType getOffsetType() {
        return Block.OffsetType.NONE;
    }

    @Override
    public void onEntityCollision(World world, BlockPos pos, BlockState state, Entity entity) {
        if (state.get(STAGE) == Stage.CAPPED) {
            entity.attackEntityFrom(BLADESHROOM_DAMAGE, 1.0F);
        }
    }

    @Override
    public void updateTick(World world, BlockPos pos, BlockState state, Random random) {
        if (random.nextInt(REGROW_CHANCE) == 0) {
            this.incrementStage(world, pos, state);
        }
    }

    private void incrementStage(World world, BlockPos pos, BlockState state) {
        Stage nextStage = state.get(STAGE).next();
        if (nextStage == null) {
            return;
        }
        world.setBlockState(pos, state.with(STAGE, nextStage));
    }

    @Override
    protected BlockStateContainer createBlockState() {
        return new BlockStateContainer(this, STAGE);
    }

    @Override
    public boolean canGrow(World world, BlockPos pos, BlockState state, boolean isClient) {
        return state.get(STAGE) != Stage.CAPPED;
    }

    @Override
    public boolean canUseBonemeal(World world, Random rand, BlockPos pos, BlockState state) {
        return true;
    }

    @Override
    public void grow(World world, Random rand, BlockPos pos, BlockState state) {
        this.incrementStage(world, pos, state);
    }

    @Override
    public ItemStack getPickBlock(BlockState state, RayTraceResult target, World world, BlockPos pos, PlayerEntity player) {
        return new ItemStack(MidnightItems.BLADESHROOM_SPORES);
    }

    public enum Stage implements IStringSerializable {
        SPORE,
        STEM,
        CAPPED;

        @Nullable
        public Stage next() {
            Stage[] stages = values();
            int ordinal = this.ordinal();
            if (ordinal < stages.length - 1) {
                return stages[ordinal + 1];
            }
            return null;
        }

        public static Stage deserialize(int meta) {
            Stage[] stages = values();
            return stages[meta % stages.length];
        }

        @Override
        public String getName() {
            return this.name().toLowerCase(Locale.ROOT);
        }
    }
}