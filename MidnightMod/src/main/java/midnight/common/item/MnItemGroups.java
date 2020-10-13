package midnight.common.item;

import midnight.common.block.MnBlocks;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;

/**
 * This class holds all the item groups for the Midnight's creative tabs, and for categorizing recipes in folders during
 * data generation.
 *
 * @author Shadew
 * @version 0.6.0
 * @since 0.6.0
 */
public final class MnItemGroups {
    public static final ItemGroup BLOCKS = new ItemGroup("midnight.blocks") {
        @Override
        public ItemStack createIcon() {
            return new ItemStack(MnBlocks.NIGHT_GRASS_BLOCK);
        }
    };
    public static final ItemGroup DECOR = new ItemGroup("midnight.decor") {
        @Override
        public ItemStack createIcon() {
            return new ItemStack(MnBlocks.NIGHT_GRASS);
        }
    };
    public static final ItemGroup MISC = new ItemGroup("midnight.misc") {
        @Override
        public ItemStack createIcon() {
            return new ItemStack(MnItems.DARK_STICK);
        }
    };

    private MnItemGroups() {
    }
}
