package midnight.common.block;

import net.minecraft.block.Block;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ITag;

public final class MnBlockTags {
    public static final ITag.INamedTag<Block> DEAD_WOOD_LOGS = BlockTags.makeWrapperTag("midnight:dead_wood_logs");
    public static final ITag.INamedTag<Block> SHADOWROOT_LOGS = BlockTags.makeWrapperTag("midnight:shadowroot_logs");
    public static final ITag.INamedTag<Block> DARK_WILLOW_LOGS = BlockTags.makeWrapperTag("midnight:dark_willow_logs");

    private MnBlockTags() {
    }
}
