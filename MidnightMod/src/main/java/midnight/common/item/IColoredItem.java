package midnight.common.item;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@FunctionalInterface
public interface IColoredItem {
    @OnlyIn(Dist.CLIENT)
    int getColor(ItemStack stack, int tintIndex);

    default Item getItem() {
        return (Item) this;
    }
}
