package midnight.data.loottables;

import net.minecraft.block.Block;
import net.minecraft.data.loot.BlockLootTables;
import midnight.common.block.MnBlocks;
import midnight.common.registry.RegistryManager;

public class MnBlockLootTables extends BlockLootTables {

    @Override
    protected void addTables() {
        registerDropSelfLootTable(MnBlocks.NIGHT_DIRT);
    }

    @Override
    protected Iterable<Block> getKnownBlocks() {
        return RegistryManager.BLOCKS;
    }
}
