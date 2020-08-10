package midnight.data.loottables;

import midnight.common.block.MnBlocks;
import midnight.common.registry.RegistryManager;
import net.minecraft.block.Block;
import net.minecraft.data.loot.BlockLootTables;
import net.minecraft.world.storage.loot.ConstantRange;
import net.minecraft.world.storage.loot.LootPool;
import net.minecraft.world.storage.loot.LootTable;

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
        registerSilkTouch(MnBlocks.NIGHT_BEDROCK);
        registerLootTable(MnBlocks.NIGHT_GRASS, onlyWithShears(MnBlocks.NIGHT_GRASS));
        registerLootTable(MnBlocks.TALL_NIGHT_GRASS, onlyWithShears(MnBlocks.TALL_NIGHT_GRASS));
        registerLootTable(MnBlocks.NIGHT_GRASS_BLOCK, block -> droppingWithSilkTouch(block, MnBlocks.NIGHT_DIRT));
        registerLootTable(MnBlocks.DARK_WATER, block -> droppingNothing());
    }

    @Override
    protected Iterable<Block> getKnownBlocks() {
        return RegistryManager.BLOCKS;
    }
}
