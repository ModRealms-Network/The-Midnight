package midnight.data.loottables;

import midnight.common.block.MnBlocks;
import midnight.common.item.MnItems;
import net.minecraft.advancements.criterion.EnchantmentPredicate;
import net.minecraft.advancements.criterion.ItemPredicate;
import net.minecraft.advancements.criterion.MinMaxBounds;
import net.minecraft.block.Block;
import net.minecraft.data.loot.BlockLootTables;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.item.Items;
import net.minecraft.loot.*;
import net.minecraft.loot.conditions.ILootCondition;
import net.minecraft.loot.conditions.MatchTool;
import net.minecraft.loot.conditions.TableBonus;
import net.minecraft.loot.functions.SetCount;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;

public class MnBlockLootTables extends BlockLootTables {
    private static final ILootCondition.IBuilder SILK_TOUCH = MatchTool.builder(ItemPredicate.Builder.create().enchantment(new EnchantmentPredicate(Enchantments.SILK_TOUCH, MinMaxBounds.IntBound.atLeast(1))));
    private static final ILootCondition.IBuilder NO_SILK_TOUCH = SILK_TOUCH.inverted();
    private static final ILootCondition.IBuilder SHEARS = MatchTool.builder(ItemPredicate.Builder.create().item(Items.SHEARS));
    private static final ILootCondition.IBuilder SILK_TOUCH_OR_SHEARS = SHEARS.alternative(SILK_TOUCH);
    private static final ILootCondition.IBuilder NOT_SILK_TOUCH_OR_SHEARS = SILK_TOUCH_OR_SHEARS.inverted();
    private static final float[] DEFAULT_SAPLING_DROP_RATES = {1 / 20F, 1 / 16F, 1 / 12F, 1 / 10F};

    @Override
    protected void addTables() {
        registerDropSelfLootTable(MnBlocks.NIGHT_STONE);
        registerDropSelfLootTable(MnBlocks.NIGHT_DIRT);
        registerDropSelfLootTable(MnBlocks.DECEITFUL_PEAT);
        registerDropSelfLootTable(MnBlocks.DECEITFUL_MUD);
        registerDropSelfLootTable(MnBlocks.TRENCHSTONE);
        registerDropSelfLootTable(MnBlocks.STRANGE_SAND);
        registerDropSelfLootTable(MnBlocks.COARSE_NIGHT_DIRT);
        registerDropSelfLootTable(MnBlocks.GHOST_PLANT_LEAF);
        registerDropSelfLootTable(MnBlocks.GHOST_PLANT_STEM);
        registerDropSelfLootTable(MnBlocks.GHOST_PLANT);
        registerDropSelfLootTable(MnBlocks.DEAD_WOOD);
        registerDropSelfLootTable(MnBlocks.DEAD_WOOD_LOG);
        registerDropSelfLootTable(MnBlocks.DEAD_WOOD_PLANKS);
        registerDropSelfLootTable(MnBlocks.DEAD_SAPLING);
        registerDropSelfLootTable(MnBlocks.STRIPPED_DEAD_WOOD);
        registerDropSelfLootTable(MnBlocks.STRIPPED_DEAD_WOOD_LOG);
        registerDropSelfLootTable(MnBlocks.SHADOWROOT_WOOD);
        registerDropSelfLootTable(MnBlocks.SHADOWROOT_LOG);
        registerDropSelfLootTable(MnBlocks.SHADOWROOT_PLANKS);
        registerDropSelfLootTable(MnBlocks.SHADOWROOT_SAPLING);
        registerLootTable(MnBlocks.SHADOWROOT_LEAVES, block -> droppingWithChancesAndDarkSticks(block, MnBlocks.SHADOWROOT_SAPLING, DEFAULT_SAPLING_DROP_RATES)); // TODO Stick should be dark stick later
        registerDropSelfLootTable(MnBlocks.STRIPPED_SHADOWROOT_WOOD);
        registerDropSelfLootTable(MnBlocks.STRIPPED_SHADOWROOT_LOG);
        registerDropSelfLootTable(MnBlocks.DARK_WILLOW_WOOD);
        registerDropSelfLootTable(MnBlocks.DARK_WILLOW_LOG);
        registerDropSelfLootTable(MnBlocks.DARK_WILLOW_PLANKS);
        registerDropSelfLootTable(MnBlocks.DARK_WILLOW_SAPLING);
        registerDropSelfLootTable(MnBlocks.NIGHTSHROOM_CAP);
        registerDropSelfLootTable(MnBlocks.NIGHTSHROOM_STEM);
        registerLootTable(MnBlocks.DARK_WILLOW_LEAVES, block -> droppingWithChancesAndDarkSticks(block, MnBlocks.DARK_WILLOW_SAPLING, DEFAULT_SAPLING_DROP_RATES)); // TODO Stick should be dark stick later
        registerLootTable(MnBlocks.HANGING_DARK_WILLOW_LEAVES, onlyWithShears(MnBlocks.HANGING_DARK_WILLOW_LEAVES));
        registerDropSelfLootTable(MnBlocks.STRIPPED_DARK_WILLOW_WOOD);
        registerDropSelfLootTable(MnBlocks.STRIPPED_DARK_WILLOW_LOG);
        registerLootTable(MnBlocks.NIGHT_BEDROCK, block -> droppingNothing());
        registerLootTable(MnBlocks.NIGHT_GRASS, onlyWithShears(MnBlocks.NIGHT_GRASS));
        registerLootTable(MnBlocks.TALL_NIGHT_GRASS, onlyWithShears(MnBlocks.TALL_NIGHT_GRASS));
        registerLootTable(MnBlocks.NIGHT_GRASS_BLOCK, block -> droppingWithSilkTouch(block, MnBlocks.NIGHT_DIRT));
        registerLootTable(MnBlocks.DARK_WATER, block -> droppingNothing());
    }

    protected static LootTable.Builder droppingNothing() {
        return LootTable.builder().addLootPool(LootPool.builder().rolls(ConstantRange.of(0)));
    }

    protected static LootTable.Builder droppingWithChancesAndDarkSticks(Block leaves, Block sapling, float... saplingBonus) {
        return droppingWithSilkTouchOrShears(
            leaves,
            withSurvivesExplosion(leaves, ItemLootEntry.builder(sapling))
                .acceptCondition(TableBonus.builder(Enchantments.FORTUNE, saplingBonus))
        ).addLootPool(
            LootPool.builder()
                    .rolls(ConstantRange.of(1))
                    .acceptCondition(NOT_SILK_TOUCH_OR_SHEARS)
                    .addEntry(
                        withExplosionDecay(
                            leaves,
                            ItemLootEntry.builder(MnItems.DARK_STICK)
                                         .acceptFunction(SetCount.builder(RandomValueRange.of(1, 2)))
                        ).acceptCondition(
                            TableBonus.builder(Enchantments.FORTUNE, 1 / 50F, 1 / 45F, 1 / 40F, 1 / 30F, 1 / 10F)
                        )
                    )
        );
    }


    @Override
    @SuppressWarnings("deprecation")
    protected Iterable<Block> getKnownBlocks() {
        return Registry.BLOCK
                   .stream()
                   .filter(block -> {
                       ResourceLocation loc = block.getRegistryName();
                       assert loc != null;
                       return loc.getNamespace().equals("midnight");
                   })
                   .distinct()
                   ::iterator;
    }
}
