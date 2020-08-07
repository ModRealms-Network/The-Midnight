package midnight.data.models;

import midnight.common.block.MnBlocks;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.ResourceLocation;

import java.util.function.BiConsumer;
import java.util.function.Function;

public class BlockItemModelTable extends ModelTable {
    @Override
    public void collectModels(BiConsumer<ResourceLocation, IModelGen> consumer) {
        // Full-cube blocks
        addBlocks(
            consumer, block -> InheritingModelGen.cubeAll(texture(block)),
            MnBlocks.NIGHT_DIRT,
            MnBlocks.NIGHT_STONE,
            MnBlocks.NIGHT_BEDROCK
        );

        addBlock(
            consumer, MnBlocks.NIGHT_GRASS_BLOCK,
            InheritingModelGen.grassBlock(
                "midnight:block/night_grass_block_top",
                "midnight:block/night_grass_block_side",
                "midnight:block/night_dirt",
                "midnight:block/night_grass_block_overlay"
            )
        );

        // Block model inheriting items
        addItems(
            consumer, BlockItemModelTable::inheritBlock,
            MnBlocks.NIGHT_DIRT,
            MnBlocks.NIGHT_GRASS_BLOCK,
            MnBlocks.NIGHT_STONE,
            MnBlocks.NIGHT_BEDROCK
        );
    }

    public static IModelGen inheritBlock(Item item) {
        if (!(item instanceof BlockItem)) throw new IllegalArgumentException("Not a BlockItem");
        return InheritingModelGen.inherit(blockModel(item));
    }

    private static String blockModel(Item item) {
        ResourceLocation loc = item.getRegistryName();
        assert loc != null;

        return loc.getNamespace() + ":block/" + loc.getPath();
    }

    private static String texture(Block block) {
        ResourceLocation loc = block.getRegistryName();
        assert loc != null;

        return loc.getNamespace() + ":block/" + loc.getPath();
    }

    private static String texture(Block block, String pathFormat) {
        ResourceLocation loc = block.getRegistryName();
        assert loc != null;

        return loc.getNamespace() + ":block/" + String.format(pathFormat, loc.getPath());
    }

    private static String texture(Item item) {
        ResourceLocation loc = item.getRegistryName();
        assert loc != null;

        return loc.getNamespace() + ":item/" + loc.getPath();
    }

    private static String texture(Item item, String pathFormat) {
        ResourceLocation loc = item.getRegistryName();
        assert loc != null;

        return loc.getNamespace() + ":item/" + String.format(pathFormat, loc.getPath());
    }

    public static void addBlock(BiConsumer<ResourceLocation, IModelGen> consumer, Block block, IModelGen gen) {
        ResourceLocation id = block.getRegistryName();
        assert id != null;

        ResourceLocation model = new ResourceLocation(id.getNamespace(), "block/" + id.getPath());
        consumer.accept(model, gen);
    }

    public static void addBlocks(BiConsumer<ResourceLocation, IModelGen> consumer, Function<Block, IModelGen> gen, Block... blocks) {
        for (Block block : blocks) {
            addBlock(consumer, block, gen.apply(block));
        }
    }

    public static void addItem(BiConsumer<ResourceLocation, IModelGen> consumer, Item item, IModelGen gen) {
        ResourceLocation id = item.getRegistryName();
        assert id != null;

        ResourceLocation model = new ResourceLocation(id.getNamespace(), "item/" + id.getPath());
        consumer.accept(model, gen);
    }

    public static void addItems(BiConsumer<ResourceLocation, IModelGen> consumer, Function<Item, IModelGen> gen, IItemProvider... items) {
        for (IItemProvider item : items) {
            Item i = item.asItem();
            addItem(consumer, i, gen.apply(i));
        }
    }
}
