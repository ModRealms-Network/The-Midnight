package midnight.data.loottables;

import midnight.common.block.MnBlocks;
import midnight.common.registry.RegistryManager;
import net.minecraft.block.Block;
import net.minecraft.data.loot.BlockLootTables;

public class MnBlockLootTables extends BlockLootTables {

    @Override
    protected void addTables() {
        registerDropSelfLootTable(MnBlocks.NIGHT_DIRT);
        registerDropSelfLootTable(MnBlocks.NIGHT_GRASS);
    }

    @Override
    protected Iterable<Block> getKnownBlocks() {
        return RegistryManager.BLOCKS;
    }
}
