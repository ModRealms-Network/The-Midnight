package midnight.data.blockstates;

import midnight.common.block.MnBlocks;
import net.minecraft.block.Block;
import net.minecraft.util.ResourceLocation;

import java.util.function.BiConsumer;
import java.util.function.Function;

public final class BlockstateTable {
    private BlockstateTable() {
    }

    public static void collectBlockstates(BiConsumer<Block, IBlockstateGen> consumer) {
        add(
            consumer, BlockstateTable::basic,
            MnBlocks.NIGHT_STONE,
            MnBlocks.NIGHT_BEDROCK,
            MnBlocks.DARK_WATER,
            MnBlocks.DECEITFUL_PEAT,
            MnBlocks.DECEITFUL_MUD,
            MnBlocks.TRENCHSTONE,
            MnBlocks.NIGHT_GRASS,
            MnBlocks.COARSE_NIGHT_DIRT,
            MnBlocks.GHOST_PLANT,
            MnBlocks.GIANT_GHOST_PLANT_LEAF
        );

        add(
            consumer, BlockstateTable::rotateRandomY,
            MnBlocks.NIGHT_DIRT,
            MnBlocks.NIGHT_GRASS_BLOCK
        );

        add(
            consumer, BlockstateTable::rotateRandomXY,
            MnBlocks.STRANGE_SAND
        );

        add(
            consumer, BlockstateTable::rotatedPillar,
            MnBlocks.GIANT_GHOST_PLANT_STEM
        );

        add(
            consumer, BlockstateTable::doublePlant,
            MnBlocks.TALL_NIGHT_GRASS
        );
    }

    private static IBlockstateGen basic(Block block) {
        ResourceLocation id = block.getRegistryName();
        assert id != null;

        ResourceLocation model = new ResourceLocation(id.getNamespace(), "block/" + id.getPath());
        return SelectorBlockstateGen.create(new ModelVariant(model));
    }

    private static IBlockstateGen doublePlant(Block block) {
        ResourceLocation id = block.getRegistryName();
        assert id != null;

        ResourceLocation lower = new ResourceLocation(id.getNamespace(), "block/" + id.getPath() + "_lower");
        ResourceLocation upper = new ResourceLocation(id.getNamespace(), "block/" + id.getPath() + "_upper");
        return SelectorBlockstateGen.create("half=lower", new ModelVariant(lower))
                                    .variant("half=upper", new ModelVariant(upper));
    }

    private static IBlockstateGen rotatedPillar(Block block) {
        ResourceLocation id = block.getRegistryName();
        assert id != null;

        ResourceLocation model = new ResourceLocation(id.getNamespace(), "block/" + id.getPath());
        return SelectorBlockstateGen.create("axis=y", new ModelVariant(model))
                                    .variant("axis=x", new ModelVariant(model).rotate(90, 90))
                                    .variant("axis=z", new ModelVariant(model).rotate(90, 0));
    }

    private static IBlockstateGen rotateRandomY(Block block) {
        ResourceLocation id = block.getRegistryName();
        assert id != null;

        ResourceLocation model = new ResourceLocation(id.getNamespace(), "block/" + id.getPath());
        return SelectorBlockstateGen.create(
            new ModelVariant(model).rotate(0, 0),
            new ModelVariant(model).rotate(0, 90),
            new ModelVariant(model).rotate(0, 180),
            new ModelVariant(model).rotate(0, 270)
        );
    }

    private static IBlockstateGen rotateRandomXY(Block block) {
        ResourceLocation id = block.getRegistryName();
        assert id != null;

        ResourceLocation model = new ResourceLocation(id.getNamespace(), "block/" + id.getPath());
        return SelectorBlockstateGen.create(
            new ModelVariant(model).rotate(0, 0),
            new ModelVariant(model).rotate(0, 90),
            new ModelVariant(model).rotate(0, 180),
            new ModelVariant(model).rotate(0, 270),
            new ModelVariant(model).rotate(90, 0),
            new ModelVariant(model).rotate(90, 90),
            new ModelVariant(model).rotate(90, 180),
            new ModelVariant(model).rotate(90, 270),
            new ModelVariant(model).rotate(180, 0),
            new ModelVariant(model).rotate(180, 90),
            new ModelVariant(model).rotate(180, 180),
            new ModelVariant(model).rotate(180, 270),
            new ModelVariant(model).rotate(270, 0),
            new ModelVariant(model).rotate(270, 90),
            new ModelVariant(model).rotate(270, 180),
            new ModelVariant(model).rotate(270, 270)
        );
    }

    private static void add(BiConsumer<Block, IBlockstateGen> consumer, Function<Block, IBlockstateGen> gen, Block... blocks) {
        for(Block block : blocks) {
            consumer.accept(block, gen.apply(block));
        }
    }
}
