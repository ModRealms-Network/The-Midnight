package midnight.common.registry;

import com.mojang.datafixers.util.Pair;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.IForgeRegistryEntry;

import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import midnight.MidnightInfo;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;

public class RegistryManager<E extends IForgeRegistryEntry<E>> {
    public static final RegistryManager<Block> BLOCKS = new RegistryManager<>();
    public static final RegistryManager<Item> ITEMS = new RegistryManager<>();
    public static final BlockItemRegistryManager BLOCKS_ITEMS = new BlockItemRegistryManager(BLOCKS, ITEMS);

    private final List<Entry> entries = new ArrayList<>();

    public E register(String id, E entry) {
        entries.add(new Entry(new ResourceLocation(MidnightInfo.MODID, id), entry));
        return entry;
    }

    public E register(ResourceLocation id, E entry) {
        entries.add(new Entry(id, entry));
        return entry;
    }

    public E register(E entry) {
        if (entry.getRegistryName() == null) throw new NullPointerException("Registry name is null");
        entries.add(new Entry(entry.getRegistryName(), entry));
        return entry;
    }

    public void register(IForgeRegistry<E> registry) {
        for (Entry entry : entries) {
            if (entry.entry.getRegistryName() == null) {
                entry.entry.setRegistryName(entry.id);
            }
            registry.register(entry.entry);
        }
    }

    public RegistryFiller<E, Object> fill() {
        return new RegistryFiller<>(this, () -> null);
    }

    public <C> RegistryFiller<E, C> fill(Supplier<C> config) {
        return new RegistryFiller<>(this, config);
    }

    public <C> RegistryFiller<E, C> fill(C config) {
        return new RegistryFiller<>(this, () -> config);
    }


    private class Entry {
        private final ResourceLocation id;
        private final E entry;

        private Entry(ResourceLocation id, E entry) {
            this.id = id;
            this.entry = entry;
        }
    }

    public static class RegistryFiller<E extends IForgeRegistryEntry<E>, C> {
        private final RegistryManager<E> manager;
        private final Supplier<? extends C> config;

        private RegistryFiller(RegistryManager<E> manager, Supplier<? extends C> config) {
            this.manager = manager;
            this.config = config;
        }

        public RegistryFiller<E, C> register(Function<? super C, ? extends E> factory, String... ids) {
            for (String id : ids) {
                manager.register(id, factory.apply(config.get()));
            }
            return this;
        }

        public RegistryFiller<E, C> register(BiFunction<? super C, ? super ResourceLocation, ? extends E> factory, String... ids) {
            for (String id : ids) {
                ResourceLocation resID = new ResourceLocation(MidnightInfo.MODID, id);
                manager.register(resID, factory.apply(config.get(), resID));
            }
            return this;
        }
    }

    public static class BlockItemRegistryFiller<C> {
        private final RegistryManager<Block> blockManager;
        private final RegistryManager<Item> itemManager;
        private final Supplier<? extends C> config;

        public BlockItemRegistryFiller(RegistryManager<Block> blockManager, RegistryManager<Item> itemManager, Supplier<? extends C> config) {
            this.blockManager = blockManager;
            this.itemManager = itemManager;
            this.config = config;
        }

        public BlockItemRegistryFiller<C> blockItem(Function<? super C, ? extends Pair<? extends Block, BlockItem>> factory, String... ids) {
            for (String id : ids) {
                Pair<? extends Block, BlockItem> pair = factory.apply(config.get());
                blockManager.register(id, pair.getFirst());
                itemManager.register(id, pair.getSecond());
            }
            return this;
        }

        public BlockItemRegistryFiller<C> blockItem(BiFunction<? super C, ? super ResourceLocation, ? extends Pair<? extends Block, BlockItem>> factory, String... ids) {
            for (String id : ids) {
                ResourceLocation resID = new ResourceLocation(MidnightInfo.MODID, id);
                Pair<? extends Block, BlockItem> pair = factory.apply(config.get(), resID);
                blockManager.register(resID, pair.getFirst());
                itemManager.register(resID, pair.getSecond());
            }
            return this;
        }

        public BlockItemRegistryFiller<C> blockOnly(Function<? super C, ? extends Block> factory, String... ids) {
            for (String id : ids) {
                blockManager.register(id, factory.apply(config.get()));
            }
            return this;
        }

        public BlockItemRegistryFiller<C> blockOnly(BiFunction<? super C, ? super ResourceLocation, ? extends Block> factory, String... ids) {
            for (String id : ids) {
                ResourceLocation resID = new ResourceLocation(MidnightInfo.MODID, id);
                blockManager.register(resID, factory.apply(config.get(), resID));
            }
            return this;
        }
    }

    public static class BlockItemRegistryManager {
        private final RegistryManager<Block> blockManager;
        private final RegistryManager<Item> itemManager;

        private BlockItemRegistryManager(RegistryManager<Block> blockManager, RegistryManager<Item> itemManager) {
            this.blockManager = blockManager;
            this.itemManager = itemManager;
        }


        public BlockItemRegistryFiller<Object> fill() {
            return new BlockItemRegistryFiller<>(blockManager, itemManager, () -> null);
        }

        public <C> BlockItemRegistryFiller<C> fill(Supplier<C> config) {
            return new BlockItemRegistryFiller<>(blockManager, itemManager, config);
        }

        public <C> BlockItemRegistryFiller<C> fill(C config) {
            return new BlockItemRegistryFiller<>(blockManager, itemManager, () -> config);
        }
    }
}
