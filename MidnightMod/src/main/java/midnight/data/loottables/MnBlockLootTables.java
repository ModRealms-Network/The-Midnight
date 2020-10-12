package midnight.data.loottables;

import midnight.common.block.MnBlocks;
import net.minecraft.block.Block;
import net.minecraft.data.loot.BlockLootTables;
import net.minecraft.loot.ConstantRange;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.LootTable;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;

public class MnBlockLootTables extends BlockLootTables {

    protected static LootTable.Builder droppingNothing() {
        return LootTable.builder().addLootPool(LootPool.builder().rolls(ConstantRange.of(0)));
    }

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
        registerDropSelfLootTable(MnBlocks.SHADOWROOT);
        registerDropSelfLootTable(MnBlocks.SHADOWROOT_LOG);
        registerDropSelfLootTable(MnBlocks.SHADOWROOT_PLANKS);
        registerDropSelfLootTable(MnBlocks.SHADOWROOT_SAPLING);
        registerLootTable(MnBlocks.SHADOWROOT_LEAVES, block -> droppingWithChancesAndSticks(block, MnBlocks.SHADOWROOT_SAPLING)); // TODO Change stick to ShadowStick
        registerDropSelfLootTable(MnBlocks.STRIPPED_SHADOWROOT);
        registerDropSelfLootTable(MnBlocks.STRIPPED_SHADOWROOT_LOG);
        registerDropSelfLootTable(MnBlocks.DARK_WILLOW);
        registerDropSelfLootTable(MnBlocks.DARK_WILLOW_LOG);
        registerDropSelfLootTable(MnBlocks.DARK_WILLOW_PLANKS);
        registerDropSelfLootTable(MnBlocks.DARK_WILLOW_SAPLING);
        registerLootTable(MnBlocks.DARK_WILLOW_LEAVES, block -> droppingWithChancesAndSticks(block, MnBlocks.DARK_WILLOW_SAPLING)); // TODO Change stick to ShadowStick
        registerLootTable(MnBlocks.DARK_WILLOW_HANGING_LEAVES, onlyWithShears(MnBlocks.DARK_WILLOW_HANGING_LEAVES));
        registerDropSelfLootTable(MnBlocks.STRIPPED_DARK_WILLOW);
        registerDropSelfLootTable(MnBlocks.STRIPPED_DARK_WILLOW_LOG);
        registerSilkTouch(MnBlocks.NIGHT_BEDROCK); // What?!
        registerLootTable(MnBlocks.NIGHT_GRASS, onlyWithShears(MnBlocks.NIGHT_GRASS));
        registerLootTable(MnBlocks.TALL_NIGHT_GRASS, onlyWithShears(MnBlocks.TALL_NIGHT_GRASS));
        registerLootTable(MnBlocks.NIGHT_GRASS_BLOCK, block -> droppingWithSilkTouch(block, MnBlocks.NIGHT_DIRT));
        registerLootTable(MnBlocks.DARK_WATER, block -> droppingNothing());
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
