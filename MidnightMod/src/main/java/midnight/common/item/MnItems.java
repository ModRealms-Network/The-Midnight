package midnight.common.item;

import midnight.common.Midnight;
import midnight.core.util.IRegistry;
import net.minecraft.item.Food;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraftforge.registries.ObjectHolder;

import javax.annotation.Nonnull;

/**
 * This class registers and stores the list of Midnight items.
 *
 * @version 0.6.0
 * @since 0.6.0
 */
@ObjectHolder("midnight")
public final class MnItems {
    public static final Item DARK_STICK = inj();

    private MnItems() {
    }

    public static void registerItems(IRegistry<Item> registry) {
        registry.registerAll(
            item("dark_stick", MnItemGroups.MISC)
        );
    }

    private static Item item(String id, Item item) {
        item.setRegistryName(Midnight.resLoc(id));
        return item;
    }

    private static Item item(String id, Item.Properties properties) {
        return item(id, new Item(properties));
    }

    private static Item item(String id, ItemGroup group) {
        return item(id, new Item.Properties().group(group));
    }

    private static Item edible(String id, ItemGroup group, Food food) {
        return item(id, new Item.Properties().group(group).food(food));
    }

    @Nonnull
    @SuppressWarnings("ConstantConditions")
    private static <T extends Item> T inj() {
        return null;
    }
}
