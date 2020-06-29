package midnight.common.block;

import com.mojang.datafixers.util.Pair;
import net.minecraftforge.registries.ObjectHolder;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.item.BlockItem;
import midnight.common.registry.BlockItemBuilder;
import midnight.common.registry.RegistryManager;

@ObjectHolder("midnight")
public final class MnBlocks {
    public static final Block NIGHT_DIRT = register(
            "night_dirt",
            BlockItemBuilder.builder(Block::new)
                            .material(Material.EARTH)
                            .color(MaterialColor.BLACK)
                            .sound(SoundType.field_235579_A_)
                            .makeBlockAndItem()
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
}
