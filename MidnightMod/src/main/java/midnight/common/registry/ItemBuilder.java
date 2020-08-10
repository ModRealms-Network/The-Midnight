package midnight.common.registry;

import net.minecraft.item.Item;

import java.util.function.Consumer;
import java.util.function.Function;

/**
 * Item builder class to wrap around {@link Item.Properties} for clarity and the ease of building items.
 */
public class ItemBuilder<I extends Item> extends AbstractItemBuilder<ItemBuilder<I>, I> {
    private Function<? super Item.Properties, ? extends I> factory;

    private ItemBuilder(Function<? super Item.Properties, ? extends I> factory) {
        this.factory = factory;
    }

    /**
     * Makes and post-processes an item configured with this builder.
     */
    public I makeItem() {
        I item = factory.apply(makeItemProps());
        for(Consumer<? super I> processor : itemProcess) {
            processor.accept(item);
        }
        return item;
    }

    /**
     * Set the factory that creates the item. The factory must not be null. If you create a builder yourself prefer
     * specifying your factory directly to {@link #builder}.
     */
    public ItemBuilder<I> factory(Function<? super Item.Properties, ? extends I> factory) {
        this.factory = factory;
        return this;
    }

    /**
     * Creates a new builder for the given factory. The factory must not be null.
     */
    public static <I extends Item> ItemBuilder<I> builder(Function<? super Item.Properties, ? extends I> factory) {
        return new ItemBuilder<>(factory);
    }
}
