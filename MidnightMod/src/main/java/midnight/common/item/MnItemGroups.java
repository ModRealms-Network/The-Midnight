package midnight.common.item;

import midnight.common.block.MnBlocks;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;

public class MnItemGroups {
    public static final ItemGroup BLOCKS = new ItemGroup("midnight.blocks") {
        @Override
        public ItemStack createIcon() {
            return new ItemStack(MnBlocks.NIGHT_GRASS_BLOCK);
        }
    };
}
