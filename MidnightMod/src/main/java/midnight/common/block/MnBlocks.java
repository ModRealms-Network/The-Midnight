package midnight.common.block;

import com.mojang.datafixers.util.Pair;
import midnight.common.block.color.NightGrassColor;
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
    private static final Factory<Block> STONE = factory(
        () -> BlockItemBuilder.builder(Block::new)
                              .material(Material.ROCK)
                              .sound(SoundType.STONE)
                              .strength(0.5, 6)
                              .harvestTool(ToolType.PICKAXE)
    );

    private static final Factory<Block> DIRT = factory(
        () -> BlockItemBuilder.builder(Block::new)
                              .material(Material.EARTH)
                              .sound(SoundType.GROUND)
                              .strength(0.5)
                              .harvestTool(ToolType.SHOVEL)
    );

    public static final Block NIGHT_STONE = STONE.blockItem("night_stone", config -> config.color(MaterialColor.BLACK));
    public static final Block NIGHT_BEDROCK = STONE.blockItem("night_bedrock", config -> config.color(MaterialColor.BLACK).unbreakable());

    public static final Block NIGHT_DIRT = DIRT.blockItem("night_dirt", config -> config.color(MaterialColor.BLACK));
    public static final Block NIGHT_GRASS_BLOCK = DIRT.blockItem(
        "night_grass_block",
        config -> config.color(MaterialColor.PURPLE_TERRACOTTA)
                        .strength(0.6)
                        .renderLayer(BlockLayer.CUTOUT_MIPPED)
                        .multiplier(new NightGrassColor())
    );




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
