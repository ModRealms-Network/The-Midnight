package midnight.data.models;

import midnight.common.block.MnBlocks;
import midnight.common.block.ShroomCapBlock;
import midnight.data.models.modelgen.IModelGen;
import midnight.data.models.stategen.IBlockStateGen;
import midnight.data.models.stategen.ModelInfo;
import midnight.data.models.stategen.VariantBlockStateGen;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.state.StateContainer;
import net.minecraft.util.ResourceLocation;

import java.util.function.BiConsumer;
import java.util.function.Function;

import static midnight.data.models.modelgen.InheritingModelGen.*;

public final class BlockStateTable {
    private static BiConsumer<Block, IBlockStateGen> consumer;

    public static void registerBlockStates(BiConsumer<Block, IBlockStateGen> c) {
        consumer = c;

        // Please keep these on single lines for the ease of line duplicating

        register(MnBlocks.NIGHT_STONE, block -> simple(name(block, "block/%s"), cubeAll(name(block, "block/%s"))));
        register(MnBlocks.NIGHT_BEDROCK, block -> simple(name(block, "block/%s"), cubeAll(name(block, "block/%s"))));
        register(MnBlocks.TRENCHSTONE, block -> simple(name(block, "block/%s"), cubeAll(name(block, "block/%s"))));

        register(MnBlocks.NIGHT_DIRT, block -> rotateY(name(block, "block/%s"), cubeAll(name(block, "block/%s"))));
        register(MnBlocks.NIGHT_GRASS_BLOCK, block -> rotateY(name(block, "block/%s"), grassBlock(name(block, "block/%s_top"), name(block, "block/%s_side"), name(block, "block/night_dirt"), name(block, "block/%s_overlay"))));
        register(MnBlocks.COARSE_NIGHT_DIRT, block -> rotateY(name(block, "block/%s"), cubeAll(name(block, "block/%s"))));
        register(MnBlocks.DECEITFUL_MUD, block -> simple(name(block, "block/%s"), cubeAll(name(block, "block/%s"))));
        register(MnBlocks.DECEITFUL_PEAT, block -> simple(name(block, "block/%s"), cubeAll(name(block, "block/%s"))));
        register(MnBlocks.STRANGE_SAND, block -> rotateXY(name(block, "block/%s"), cubeAll(name(block, "block/%s"))));

        register(MnBlocks.NIGHT_GRASS, block -> simple(name(block, "block/%s"), tintedCross(name(block, "block/%s"))));
        register(MnBlocks.TALL_NIGHT_GRASS, block -> doublePlant(name(block, "block/%s_lower"), tintedCross(name(block, "block/%s_lower")), name(block, "block/%s_upper"), tintedCross(name(block, "block/%s_upper"))));

        register(MnBlocks.DARK_WATER, block -> none());

        register(MnBlocks.GHOST_PLANT, block -> simple(name(block, "block/%s"), cross(name(block, "block/%s"))));
        register(MnBlocks.GHOST_PLANT_LEAF, block -> simple(name(block, "block/%s"), cubeAll(name(block, "block/%s"))));
        register(MnBlocks.GHOST_PLANT_STEM, block -> rotatedPillar(name(block, "block/%s"), cubeColumn(name(block, "block/%s_end"), name(block, "block/%s_side"))));

        register(MnBlocks.DEAD_WOOD_LOG, block -> rotatedPillar(name(block, "block/%s"), cubeColumn(name(block, "block/%s_end"), name(block, "block/%s_side"))));
        register(MnBlocks.STRIPPED_DEAD_WOOD_LOG, block -> rotatedPillar(name(block, "block/%s"), cubeColumn(name(block, "block/%s_end"), name(block, "block/%s_side"))));
        register(MnBlocks.DEAD_WOOD, block -> rotatedPillar(name(block, "block/%s"), cubeAll(name(block, "block/%s_log_side"))));
        register(MnBlocks.STRIPPED_DEAD_WOOD, block -> rotatedPillar(name(block, "block/%s"), cubeAll(name(block, "block/%s_log_side"))));
        register(MnBlocks.DEAD_WOOD_PLANKS, block -> simple(name(block, "block/%s"), cubeAll(name(block, "block/%s"))));
        register(MnBlocks.DEAD_SAPLING, block -> simple(name(block, "block/%s"), cross(name(block, "block/%s"))));

        register(MnBlocks.SHADOWROOT_LOG, block -> rotatedPillar(name(block, "block/%s"), cubeColumn(name(block, "block/%s_end"), name(block, "block/%s_side"))));
        register(MnBlocks.STRIPPED_SHADOWROOT_LOG, block -> rotatedPillar(name(block, "block/%s"), cubeColumn(name(block, "block/%s_end"), name(block, "block/%s_side"))));
        register(MnBlocks.SHADOWROOT_WOOD, block -> rotatedPillar(name(block, "block/%s"), cubeAll(name(block, "block/%s_log_side", "_wood"))));
        register(MnBlocks.STRIPPED_SHADOWROOT_WOOD, block -> rotatedPillar(name(block, "block/%s"), cubeAll(name(block, "block/%s_log_side", "_wood"))));
        register(MnBlocks.SHADOWROOT_LEAVES, block -> simple(name(block, "block/%s"), leaves(name(block, "block/%s"))));
        register(MnBlocks.SHADOWROOT_PLANKS, block -> simple(name(block, "block/%s"), cubeAll(name(block, "block/%s"))));
        register(MnBlocks.SHADOWROOT_SAPLING, block -> simple(name(block, "block/%s"), cross(name(block, "block/%s"))));


        register(MnBlocks.DARK_WILLOW_LOG, block -> rotatedPillar(name(block, "block/%s"), cubeColumn(name(block, "block/%s_end"), name(block, "block/%s_side"))));
        register(MnBlocks.STRIPPED_DARK_WILLOW_LOG, block -> rotatedPillar(name(block, "block/%s"), cubeColumn(name(block, "block/%s_end"), name(block, "block/%s_side"))));
        register(MnBlocks.DARK_WILLOW_WOOD, block -> rotatedPillar(name(block, "block/%s"), cubeAll(name(block, "block/%s_log_side", "_wood"))));
        register(MnBlocks.STRIPPED_DARK_WILLOW_WOOD, block -> rotatedPillar(name(block, "block/%s"), cubeAll(name(block, "block/%s_log_side", "_wood"))));
        register(MnBlocks.DARK_WILLOW_LEAVES, block -> simple(name(block, "block/%s"), leaves(name(block, "block/%s"))));
        register(MnBlocks.HANGING_DARK_WILLOW_LEAVES, block -> hangingPlant(name(block, "block/%s_end"), hangingLeavesEnd(name(block, "block/%s_inner"), name(block, "block/%s_end")), name(block, "block/%s"), hangingLeavesRoot(name(block, "block/%s_inner"), name(block, "block/%s_outer"))));
        register(MnBlocks.DARK_WILLOW_PLANKS, block -> simple(name(block, "block/%s"), cubeAll(name(block, "block/%s"))));
        register(MnBlocks.DARK_WILLOW_SAPLING, block -> simple(name(block, "block/%s"), cross(name(block, "block/%s"))));

        register(MnBlocks.SHROOM_AIR, block -> none());

        register(MnBlocks.NIGHTSHROOM_CAP, block -> shroomCap(block, name(block, "block/%s"), name(block, "block/%s"), name(block, "block/%s_inner")));
        register(MnBlocks.NIGHTSHROOM_STEM, block -> simple(name(block, "block/%s"), cubeAll(name(block, "block/%s"))));
    }

    private static IBlockStateGen none() {
        return VariantBlockStateGen.create(ModelInfo.create("midnight:block/nomodel"));
    }

    private static IBlockStateGen simple(String name, IModelGen model) {
        return VariantBlockStateGen.create(ModelInfo.create(name, model));
    }

    private static IBlockStateGen hangingPlant(String endName, IModelGen endModel, String rootName, IModelGen rootModel) {
        return VariantBlockStateGen.create("end=true", ModelInfo.create(endName, endModel))
                                   .variant("end=false", ModelInfo.create(rootName, rootModel));
    }

    private static IBlockStateGen rotateY(String name, IModelGen model) {
        return VariantBlockStateGen.create(
            ModelInfo.create(name, model).rotate(0, 0),
            ModelInfo.create(name, model).rotate(0, 90),
            ModelInfo.create(name, model).rotate(0, 180),
            ModelInfo.create(name, model).rotate(0, 270)
        );
    }

    private static IBlockStateGen rotateXY(String name, IModelGen model) {
        return VariantBlockStateGen.create(
            ModelInfo.create(name, model).rotate(0, 0),
            ModelInfo.create(name, model).rotate(0, 90),
            ModelInfo.create(name, model).rotate(0, 180),
            ModelInfo.create(name, model).rotate(0, 270),
            ModelInfo.create(name, model).rotate(90, 0),
            ModelInfo.create(name, model).rotate(90, 90),
            ModelInfo.create(name, model).rotate(90, 180),
            ModelInfo.create(name, model).rotate(90, 270),
            ModelInfo.create(name, model).rotate(180, 0),
            ModelInfo.create(name, model).rotate(180, 90),
            ModelInfo.create(name, model).rotate(180, 180),
            ModelInfo.create(name, model).rotate(180, 270),
            ModelInfo.create(name, model).rotate(270, 0),
            ModelInfo.create(name, model).rotate(270, 90),
            ModelInfo.create(name, model).rotate(270, 180),
            ModelInfo.create(name, model).rotate(270, 270)
        );
    }

    private static IBlockStateGen doublePlant(String lower, IModelGen lowerModel, String upper, IModelGen upperModel) {
        return VariantBlockStateGen.create("half=lower", ModelInfo.create(lower, lowerModel))
                                   .variant("half=upper", ModelInfo.create(upper, upperModel));
    }

    private static IBlockStateGen rotatedPillar(String name, IModelGen model) {
        return VariantBlockStateGen.create("axis=y", ModelInfo.create(name, model).rotate(0, 0))
                                   .variant("axis=z", ModelInfo.create(name, model).rotate(90, 0))
                                   .variant("axis=x", ModelInfo.create(name, model).rotate(90, 90));
    }

    private static IBlockStateGen shroomCap(Block block, String name, String capTex, String innerTex) {
        VariantBlockStateGen gen = new VariantBlockStateGen();
        StateContainer<Block, BlockState> states = block.getStateContainer();

        // Yay data generation - skips us writing 64 different variants in a JSON file
        for (BlockState state : states.getValidStates()) {
            String up = state.get(ShroomCapBlock.UP) ? capTex : innerTex;
            String down = state.get(ShroomCapBlock.DOWN) ? capTex : innerTex;
            String north = state.get(ShroomCapBlock.NORTH) ? capTex : innerTex;
            String east = state.get(ShroomCapBlock.EAST) ? capTex : innerTex;
            String south = state.get(ShroomCapBlock.SOUTH) ? capTex : innerTex;
            String west = state.get(ShroomCapBlock.WEST) ? capTex : innerTex;

            String side = "";
            if (!state.get(ShroomCapBlock.UP)) side += "u";
            if (!state.get(ShroomCapBlock.DOWN)) side += "d";
            if (!state.get(ShroomCapBlock.NORTH)) side += "n";
            if (!state.get(ShroomCapBlock.EAST)) side += "e";
            if (!state.get(ShroomCapBlock.SOUTH)) side += "s";
            if (!state.get(ShroomCapBlock.WEST)) side += "w";
            if (!side.isEmpty()) side = "_" + side;

            gen.variant(state, ModelInfo.create(name + side, cube(north, east, south, west, up, down).texture("particle", innerTex)));
        }
        return gen;
    }

    private static void register(Block block, Function<Block, IBlockStateGen> genFactory) {
        consumer.accept(block, genFactory.apply(block));
    }

    private static String name(Block block, String nameFormat) {
        ResourceLocation id = block.getRegistryName();
        assert id != null;

        return String.format("%s:%s", id.getNamespace(), String.format(nameFormat, id.getPath()));
    }

    private static String name(Block block, String nameFormat, String removeSuffix) {
        ResourceLocation id = block.getRegistryName();
        assert id != null;

        String pth = id.getPath();
        if (pth.endsWith(removeSuffix))
            pth = pth.substring(0, pth.length() - removeSuffix.length());

        return String.format("%s:%s", id.getNamespace(), String.format(nameFormat, pth));
    }

    private static String name(Block block) {
        ResourceLocation id = block.getRegistryName();
        assert id != null;
        return id.toString();
    }


    private BlockStateTable() {
    }
}
