package midnight.common.registry;

import net.minecraft.item.Item;

import java.util.function.Function;

public class ItemBuilder<I extends Item> extends AbstractItemBuilder<ItemBuilder<I>> {
    private final Function<? super Item.Properties, ? extends I> factory;

    private ItemBuilder(Function<? super Item.Properties, ? extends I> factory) {
        this.factory = factory;
    }

    public I build() {
        return factory.apply(makeItemProps());
    }

    public static <I extends Item> ItemBuilder<I> builder(Function<? super Item.Properties, ? extends I> factory) {
        return new ItemBuilder<>(factory);
    }
}
