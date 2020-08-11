package midnight.data.models;

import com.mojang.datafixers.util.Pair;
import midnight.common.Midnight;
import midnight.common.block.MnBlocks;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.ResourceLocation;

import java.util.function.BiConsumer;
import java.util.function.Function;

public class BlockModelTable extends ModelTable {
    @Override
    public void collectModels(BiConsumer<ResourceLocation, IModelGen> consumer) {
        // Full-cube blocks
        addBlocks(
            consumer, block -> InheritingModelGen.cubeAll(texture(block)),
            MnBlocks.NIGHT_DIRT,
            MnBlocks.NIGHT_STONE,
            MnBlocks.NIGHT_BEDROCK,
            MnBlocks.DECEITFUL_PEAT,
            MnBlocks.DECEITFUL_MUD,
            MnBlocks.TRENCHSTONE,
            MnBlocks.STRANGE_SAND,
            MnBlocks.COARSE_NIGHT_DIRT,
            MnBlocks.GIANT_GHOST_PLANT_LEAF
        );

        // Cross plants
        addBlocks(
            consumer, block -> InheritingModelGen.cross(texture(block)),
            MnBlocks.GHOST_PLANT
        );

        // Column blocks
        addBlocks(
            consumer, block -> InheritingModelGen.cubeColumn(texture(block, "%s_end"), texture(block, "%s_side")),
            MnBlocks.GIANT_GHOST_PLANT_STEM
        );

        // Night grass
        addBlock(
            consumer, MnBlocks.NIGHT_GRASS_BLOCK,
            InheritingModelGen.grassBlock(
                "midnight:block/night_grass_block_top",
                "midnight:block/night_grass_block_side",
                "midnight:block/night_dirt",
                "midnight:block/night_grass_block_overlay"
            )
        );
        addBlock(
            consumer, MnBlocks.NIGHT_GRASS,
            InheritingModelGen.tintedCross("midnight:block/night_grass")
        );
        addDoublePlant(
            consumer, MnBlocks.TALL_NIGHT_GRASS,
            InheritingModelGen.tintedCross("midnight:block/tall_night_grass_lower"),
            InheritingModelGen.tintedCross("midnight:block/tall_night_grass_upper")
        );

        // Block model inheriting items
        addItems(
            consumer, BlockModelTable::inheritBlock,
            MnBlocks.NIGHT_DIRT,
            MnBlocks.NIGHT_GRASS_BLOCK,
            MnBlocks.NIGHT_STONE,
            MnBlocks.NIGHT_BEDROCK,
            MnBlocks.DECEITFUL_PEAT,
            MnBlocks.DECEITFUL_MUD,
            MnBlocks.TRENCHSTONE,
            MnBlocks.STRANGE_SAND,
            MnBlocks.GIANT_GHOST_PLANT_LEAF,
            MnBlocks.GIANT_GHOST_PLANT_STEM
        );

        // Layered items
        addItems(
            consumer, BlockModelTable::bgenerated,
            MnBlocks.NIGHT_GRASS,
            MnBlocks.TALL_NIGHT_GRASS,
            MnBlocks.GHOST_PLANT
        );
        addItems(
            consumer, BlockModelTable::bgenerated,
            MnBlocks.NIGHT_GRASS
        );
        addItems(
            consumer, BlockModelTable::generatedDoublePlant,
            MnBlocks.TALL_NIGHT_GRASS
        );
    }

    public static IModelGen inheritBlock(Item item) {
        if(!(item instanceof BlockItem)) throw new IllegalArgumentException("Not a BlockItem");
        return InheritingModelGen.inherit(blockModel(item));
    }

    public static IModelGen generated(Item item) {
        return InheritingModelGen.generatedItem(texture(item));
    }

    public static IModelGen bgenerated(Item item) {
        return InheritingModelGen.generatedItem(btexture(item));
    }

    public static IModelGen generatedDoublePlant(Item item) {
        return InheritingModelGen.generatedItem(btexture(item, "%s_upper"));
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

    private static String btexture(Item item) {
        ResourceLocation loc = item.getRegistryName();
        assert loc != null;

        return loc.getNamespace() + ":block/" + loc.getPath();
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

    private static String btexture(Item item, String pathFormat) {
        ResourceLocation loc = item.getRegistryName();
        assert loc != null;

        return loc.getNamespace() + ":block/" + String.format(pathFormat, loc.getPath());
    }

    public static void addBlock(BiConsumer<ResourceLocation, IModelGen> consumer, Block block, IModelGen gen) {
        ResourceLocation id = block.getRegistryName();
        assert id != null;

        ResourceLocation model = new ResourceLocation(id.getNamespace(), "block/" + id.getPath());
        consumer.accept(model, gen);
    }

    public static void addDoublePlant(BiConsumer<ResourceLocation, IModelGen> consumer, Block block, IModelGen lower, IModelGen upper) {
        ResourceLocation id = block.getRegistryName();
        assert id != null;

        ResourceLocation lo = new ResourceLocation(id.getNamespace(), "block/" + id.getPath() + "_lower");
        ResourceLocation hi = new ResourceLocation(id.getNamespace(), "block/" + id.getPath() + "_upper");
        consumer.accept(lo, lower);
        consumer.accept(hi, upper);
    }

    public static void addNamed(BiConsumer<ResourceLocation, IModelGen> consumer, String block, IModelGen gen) {
        ResourceLocation model = Midnight.resLoc(block);
        consumer.accept(model, gen);
    }

    public static void addBlocks(BiConsumer<ResourceLocation, IModelGen> consumer, Function<Block, IModelGen> gen, Block... blocks) {
        for(Block block : blocks) {
            addBlock(consumer, block, gen.apply(block));
        }
    }

    public static void addDoublePlants(BiConsumer<ResourceLocation, IModelGen> consumer, Function<Block, Pair<IModelGen, IModelGen>> gen, Block... blocks) {
        for(Block block : blocks) {
            Pair<IModelGen, IModelGen> pair = gen.apply(block);
            addDoublePlant(consumer, block, pair.getFirst(), pair.getSecond());
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
