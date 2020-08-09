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
            MnBlocks.TRENCHSTONE
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
    }

    private static IBlockstateGen basic(Block block) {
        ResourceLocation id = block.getRegistryName();
        assert id != null;

        ResourceLocation model = new ResourceLocation(id.getNamespace(), "block/" + id.getPath());
        return SelectorBlockstateGen.create(new ModelVariant(model));
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
