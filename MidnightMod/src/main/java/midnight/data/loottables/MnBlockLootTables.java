package midnight.data.loottables;

import midnight.common.block.MnBlocks;
import midnight.common.registry.RegistryManager;
import net.minecraft.block.Block;
import net.minecraft.data.loot.BlockLootTables;

public class MnBlockLootTables extends BlockLootTables {

    @Override
    protected void addTables() {
        registerDropSelfLootTable(MnBlocks.NIGHT_STONE);
        registerDropSelfLootTable(MnBlocks.NIGHT_DIRT);
        registerSilkTouch(MnBlocks.NIGHT_BEDROCK);
        registerLootTable(MnBlocks.NIGHT_GRASS_BLOCK, block -> droppingWithSilkTouch(block, MnBlocks.NIGHT_DIRT));
    }

    @Override
    protected Iterable<Block> getKnownBlocks() {
        return RegistryManager.BLOCKS;
    }
}
