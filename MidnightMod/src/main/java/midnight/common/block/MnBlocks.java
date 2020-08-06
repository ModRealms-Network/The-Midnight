package midnight.common.block;

import com.mojang.datafixers.util.Pair;
import midnight.common.registry.BlockItemBuilder;
import midnight.common.registry.RegistryManager;
import midnight.core.util.BlockLayer;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.item.BlockItem;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.registries.ObjectHolder;

import java.util.function.Function;
import java.util.function.Supplier;

/*
 * We use our own Registry Manager to register blocks for The Midnight.
 * This makes adding and developing new blocks into the mod easier for everyone involved.
 */
@ObjectHolder("midnight")
public final class MnBlocks {
    private static final Factory<Block> DIRT = factory(
        () -> BlockItemBuilder.builder(Block::new)
                              .material(Material.EARTH)
                              .color(MaterialColor.BLACK)
                              .sound(SoundType.GROUND)
                              .strength(0.5)
                              .harvestTool(ToolType.SHOVEL)
    );

    public static final Block NIGHT_DIRT = DIRT.blockItem("night_dirt");
    public static final Block NIGHT_GRASS = DIRT.blockItem("night_grass", config -> config.renderLayer(BlockLayer.CUTOUT_MIPPED));




    private MnBlocks() {
    }

    private static <B extends Block> B register(String id, B block) {
        RegistryManager.BLOCKS.register(id, block);
        return block;
    }

    private static <B extends Block> B register(String id, Pair<? extends B, ? extends BlockItem> blockItemPair) {
        RegistryManager.BLOCKS.register(id, blockItemPair.getFirst());
        RegistryManager.ITEMS.register(id, blockItemPair.getSecond());
        return blockItemPair.getFirst();
    }

    private static <B extends Block> Factory<B> factory(Supplier<BlockItemBuilder<B>> config) {
        return new Factory<>(config);
    }

    private static class Factory<B extends Block> {
        private final Supplier<BlockItemBuilder<B>> builderSupplier;

        private Factory(Supplier<BlockItemBuilder<B>> builderSupplier) {
            this.builderSupplier = builderSupplier;
        }

        public B block(String id, Function<BlockItemBuilder<B>, BlockItemBuilder<B>> additionalConfig) {
            return register(id, additionalConfig.apply(builderSupplier.get()).makeBlock());
        }

        public B block(String id) {
            return block(id, Function.identity());
        }

        public B blockItem(String id, Function<BlockItemBuilder<B>, BlockItemBuilder<B>> additionalConfig) {
            return register(id, additionalConfig.apply(builderSupplier.get()).makeBlockAndItem());
        }

        public B blockItem(String id) {
            return blockItem(id, Function.identity());
        }
    }
}
