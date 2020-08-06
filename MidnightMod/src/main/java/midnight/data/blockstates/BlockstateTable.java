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
            MnBlocks.NIGHT_DIRT,
            MnBlocks.NIGHT_GRASS
        );
    }

    private static IBlockstateGen basic(Block block) {
        ResourceLocation id = block.getRegistryName();
        assert id != null;

        ResourceLocation model = new ResourceLocation(id.getNamespace(), "block/" + id.getPath());
        return SelectorBlockstateGen.create(new ModelVariant(model));
    }

    private static void add(BiConsumer<Block, IBlockstateGen> consumer, Function<Block, IBlockstateGen> gen, Block... blocks) {
        for (Block block : blocks) {
            consumer.accept(block, gen.apply(block));
        }
    }
}
