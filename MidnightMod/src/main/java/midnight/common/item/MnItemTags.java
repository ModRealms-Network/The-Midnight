package midnight.common.item;

import net.minecraft.item.Item;
import net.minecraft.tags.ITag;
import net.minecraft.tags.ItemTags;

public final class MnItemTags {
    public static final ITag.INamedTag<Item> DEAD_WOOD_LOGS = ItemTags.makeWrapperTag("midnight:dead_wood_logs");
    public static final ITag.INamedTag<Item> SHADOWROOT_LOGS = ItemTags.makeWrapperTag("midnight:shadowroot_logs");
    public static final ITag.INamedTag<Item> DARK_WILLOW_LOGS = ItemTags.makeWrapperTag("midnight:dark_willow_logs");

    private MnItemTags() {
    }
}
