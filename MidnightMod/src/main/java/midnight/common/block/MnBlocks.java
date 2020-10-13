package midnight.common.block;

import midnight.client.MidnightClient;
import midnight.common.Midnight;
import midnight.common.block.fluid.MnFluids;
import midnight.common.item.MnItemGroups;
import midnight.common.world.biome.MnBiomeColors;
import midnight.core.util.ColorUtil;
import midnight.core.util.IRegistry;
import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraft.client.renderer.color.BlockColors;
import net.minecraft.client.renderer.color.ItemColors;
import net.minecraft.fluid.FlowingFluid;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.tags.ITag;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.registries.ObjectHolder;

import javax.annotation.Nonnull;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * This class registers and stores the list of Midnight blocks and their respective block items.
 *
 * @author Shadew
 * @version 0.6.0
 * @since 0.6.0
 */
@ObjectHolder("midnight")
public final class MnBlocks {

    public static final Block NIGHT_STONE = inj();
    public static final Block NIGHT_BEDROCK = inj();
    public static final Block TRENCHSTONE = inj();

    public static final Block NIGHT_DIRT = inj();
    public static final Block COARSE_NIGHT_DIRT = inj();
    public static final Block NIGHT_GRASS_BLOCK = inj();
    public static final Block DECEITFUL_PEAT = inj();
    public static final Block DECEITFUL_MUD = inj();
    public static final Block STRANGE_SAND = inj();

    public static final Block DARK_WATER = inj();

    public static final Block NIGHT_GRASS = inj();
    public static final Block TALL_NIGHT_GRASS = inj();

    public static final Block GHOST_PLANT_STEM = inj();
    public static final Block GHOST_PLANT_LEAF = inj();
    public static final Block GHOST_PLANT = inj();

    public static final Block DEAD_WOOD_LOG = inj();
    public static final Block STRIPPED_DEAD_WOOD_LOG = inj();
    public static final Block DEAD_WOOD = inj();
    public static final Block STRIPPED_DEAD_WOOD = inj();
    public static final Block DEAD_WOOD_PLANKS = inj();
    public static final Block DEAD_SAPLING = inj();

    public static final Block SHADOWROOT_LOG = inj();
    public static final Block STRIPPED_SHADOWROOT_LOG = inj();
    public static final Block SHADOWROOT_WOOD = inj();
    public static final Block STRIPPED_SHADOWROOT_WOOD = inj();
    public static final Block SHADOWROOT_LEAVES = inj();
    public static final Block SHADOWROOT_PLANKS = inj();
    public static final Block SHADOWROOT_SAPLING = inj();

    public static final Block DARK_WILLOW_LOG = inj();
    public static final Block STRIPPED_DARK_WILLOW_LOG = inj();
    public static final Block DARK_WILLOW_WOOD = inj();
    public static final Block STRIPPED_DARK_WILLOW_WOOD = inj();
    public static final Block DARK_WILLOW_LEAVES = inj();
    public static final Block HANGING_DARK_WILLOW_LEAVES = inj();
    public static final Block DARK_WILLOW_PLANKS = inj();
    public static final Block DARK_WILLOW_SAPLING = inj();

    public static void registerBlocks(IRegistry<Block> registry) {
        registry.registerAll(
            stone("night_stone", 1.5, 6, MaterialColor.OBSIDIAN),
            stone("night_bedrock", 1.5, 6, MaterialColor.LIGHT_GRAY_TERRACOTTA),
            stone("trenchstone", 1.5, 6, MaterialColor.BLACK),

            dirt("night_dirt", MaterialColor.BLACK),
            dirt("coarse_night_dirt", MaterialColor.BLACK),
            grassBlock("night_grass_block"),
            dirt("deceitful_peat", MaterialColor.PURPLE_TERRACOTTA),
            mud("deceitful_mud"),
            sand("strange_sand"),

            water("dark_water", () -> MnFluids.DARK_WATER),

            smallGrowable("night_grass", 0, 0, Material.TALL_PLANTS, MaterialColor.PURPLE_TERRACOTTA, () -> (MnDoublePlantBlock) TALL_NIGHT_GRASS).setPlantHitbox(12, 13).setOffsetType(Block.OffsetType.XYZ),
            tallPlant("tall_night_grass", 0, 0, Material.TALL_PLANTS, MaterialColor.PURPLE_TERRACOTTA).setPlantHitbox(14, 30).setOffsetType(Block.OffsetType.XYZ),

            giantGhostPlant("ghost_plant_stem", GhostPlantStemBlock::new),
            giantGhostPlant("ghost_plant_leaf", GhostPlantBlock::new),
            emissivePlant("ghost_plant", 0, 0, 9, Material.PLANTS, MaterialColor.SNOW).setPlantHitbox(13, 14).setOffsetType(Block.OffsetType.XZ),

            log("dead_wood_log", MaterialColor.FOLIAGE, () -> STRIPPED_DEAD_WOOD_LOG),
            log("stripped_dead_wood_log", MaterialColor.FOLIAGE),
            log("dead_wood", MaterialColor.FOLIAGE, () -> STRIPPED_DEAD_WOOD),
            log("stripped_dead_wood", MaterialColor.FOLIAGE),
            wood("dead_wood_planks", MaterialColor.FOLIAGE),
            plant("dead_sapling", 0, 0, Material.PLANTS, MaterialColor.FOLIAGE).setPlantHitbox(12, 13),

            log("shadowroot_log", MaterialColor.PURPLE, () -> STRIPPED_SHADOWROOT_LOG),
            log("stripped_shadowroot_log", MaterialColor.PURPLE),
            log("shadowroot_wood", MaterialColor.PURPLE, () -> STRIPPED_SHADOWROOT_WOOD),
            log("stripped_shadowroot_wood", MaterialColor.PURPLE),
            leaves("shadowroot_leaves", MaterialColor.PURPLE),
            wood("shadowroot_planks", MaterialColor.PURPLE),
            plant("shadowroot_sapling", 0, 0, Material.PLANTS, MaterialColor.PURPLE).setPlantHitbox(11, 15),

            log("dark_willow_log", MaterialColor.BLUE, () -> STRIPPED_DARK_WILLOW_LOG),
            log("stripped_dark_willow_log", MaterialColor.BLUE),
            log("dark_willow_wood", MaterialColor.BLUE, () -> STRIPPED_DARK_WILLOW_WOOD),
            log("stripped_dark_willow_wood", MaterialColor.BLUE),
            growsHangingLeaves("dark_willow_leaves", MaterialColor.BLUE_TERRACOTTA, () -> HANGING_DARK_WILLOW_LEAVES),
            hangingLeaves("hanging_dark_willow_leaves", MaterialColor.BLUE_TERRACOTTA, () -> DARK_WILLOW_LEAVES, MnBlockTags.DARK_WILLOW_LOGS).setPlantHitbox(14, 16),
            wood("dark_willow_planks", MaterialColor.BLUE_TERRACOTTA),
            plant("dark_willow_sapling", 0, 0, Material.PLANTS, MaterialColor.BLUE_TERRACOTTA).setPlantHitbox(12, 14)
        );
    }

    public static void registerItems(IRegistry<Item> registry) {
        registry.registerAll(
            item(NIGHT_STONE, MnItemGroups.BLOCKS),
            item(NIGHT_BEDROCK, MnItemGroups.BLOCKS),
            item(TRENCHSTONE, MnItemGroups.BLOCKS),

            item(NIGHT_DIRT, MnItemGroups.BLOCKS),
            item(COARSE_NIGHT_DIRT, MnItemGroups.BLOCKS),
            item(NIGHT_GRASS_BLOCK, MnItemGroups.BLOCKS),
            item(DECEITFUL_PEAT, MnItemGroups.BLOCKS),
            item(DECEITFUL_MUD, MnItemGroups.BLOCKS),
            item(STRANGE_SAND, MnItemGroups.BLOCKS),

            item(NIGHT_GRASS, MnItemGroups.DECOR),
            item(TALL_NIGHT_GRASS, MnItemGroups.DECOR),

            item(GHOST_PLANT_STEM, MnItemGroups.BLOCKS),
            item(GHOST_PLANT_LEAF, MnItemGroups.BLOCKS),
            item(GHOST_PLANT, MnItemGroups.DECOR),

            item(DEAD_WOOD_LOG, MnItemGroups.BLOCKS),
            item(STRIPPED_DEAD_WOOD_LOG, MnItemGroups.BLOCKS),
            item(DEAD_WOOD, MnItemGroups.BLOCKS),
            item(STRIPPED_DEAD_WOOD, MnItemGroups.BLOCKS),
            item(DEAD_WOOD_PLANKS, MnItemGroups.BLOCKS),
            item(DEAD_SAPLING, MnItemGroups.BLOCKS),

            item(SHADOWROOT_LOG, MnItemGroups.BLOCKS),
            item(STRIPPED_SHADOWROOT_LOG, MnItemGroups.BLOCKS),
            item(SHADOWROOT_WOOD, MnItemGroups.BLOCKS),
            item(STRIPPED_SHADOWROOT_WOOD, MnItemGroups.BLOCKS),
            item(SHADOWROOT_LEAVES, MnItemGroups.BLOCKS),
            item(SHADOWROOT_PLANKS, MnItemGroups.BLOCKS),
            item(SHADOWROOT_SAPLING, MnItemGroups.BLOCKS),

            item(DARK_WILLOW_LOG, MnItemGroups.BLOCKS),
            item(STRIPPED_DARK_WILLOW_LOG, MnItemGroups.BLOCKS),
            item(DARK_WILLOW_WOOD, MnItemGroups.BLOCKS),
            item(STRIPPED_DARK_WILLOW_WOOD, MnItemGroups.BLOCKS),
            item(DARK_WILLOW_LEAVES, MnItemGroups.BLOCKS),
            item(HANGING_DARK_WILLOW_LEAVES, MnItemGroups.BLOCKS),
            item(DARK_WILLOW_PLANKS, MnItemGroups.BLOCKS),
            item(DARK_WILLOW_SAPLING, MnItemGroups.BLOCKS)
        );
    }

    @OnlyIn(Dist.CLIENT)
    public static void setupRenderers() {
        RenderTypeLookup.setRenderLayer(NIGHT_GRASS_BLOCK, RenderType.getCutoutMipped());

        RenderTypeLookup.setRenderLayer(NIGHT_GRASS, RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(TALL_NIGHT_GRASS, RenderType.getCutout());

        RenderTypeLookup.setRenderLayer(GHOST_PLANT_STEM, RenderType.getTranslucent());
        RenderTypeLookup.setRenderLayer(GHOST_PLANT_LEAF, RenderType.getTranslucent());
        RenderTypeLookup.setRenderLayer(GHOST_PLANT, RenderType.getCutout());

        RenderTypeLookup.setRenderLayer(DEAD_SAPLING, RenderType.getCutout());

        RenderTypeLookup.setRenderLayer(SHADOWROOT_LEAVES, RenderType.getCutoutMipped());
        RenderTypeLookup.setRenderLayer(SHADOWROOT_SAPLING, RenderType.getCutout());

        RenderTypeLookup.setRenderLayer(DARK_WILLOW_LEAVES, RenderType.getCutoutMipped());
        RenderTypeLookup.setRenderLayer(HANGING_DARK_WILLOW_LEAVES, RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(DARK_WILLOW_SAPLING, RenderType.getCutout());


        BlockColors blockColors = Minecraft.getInstance().getBlockColors();
        ItemColors itemColors = Minecraft.getInstance().getItemColors();

        blockColors.register(
            (state, world, pos, tint) -> {
                if(pos == null || world == null) return 0x9A63B8;
                return MidnightClient.get().getNightGrassColorCache().getColor(pos, MnBiomeColors.NIGHT_GRASS);
            },
            NIGHT_GRASS_BLOCK
        );
        itemColors.register(
            (stack, tint) -> 0x9A63B8,
            NIGHT_GRASS_BLOCK
        );

        blockColors.register(
            (state, world, pos, tint) -> {
                if(pos == null || world == null) return 0x8C74A1;
                int color = MidnightClient.get().getNightGrassColorCache().getColor(pos, MnBiomeColors.NIGHT_GRASS);
                color = ColorUtil.darker(color, 0.3);
                return color;
            },
            NIGHT_GRASS, TALL_NIGHT_GRASS
        );
        itemColors.register(
            (stack, tint) -> 0x8C74A1,
            NIGHT_GRASS, TALL_NIGHT_GRASS
        );

        blockColors.register(
            (state, world, pos, tint) -> {
                if(pos == null || world == null) return 0x3A3154;
                return MidnightClient.get().getShadowrootColorCache().getColor(pos, MnBiomeColors.SHADOWROOT);
            },
            SHADOWROOT_LEAVES
        );
        itemColors.register(
            (stack, tint) -> 0x3A3154,
            SHADOWROOT_LEAVES
        );
    }




    private MnBlocks() {
    }

    private static BlockItem item(Block block, Item.Properties props) {
        ResourceLocation id = block.getRegistryName();
        assert id != null;
        BlockItem item = new BlockItem(block, props);
        item.setRegistryName(id);
        return item;
    }

    private static BlockItem item(Block block, ItemGroup group) {
        return item(block, new Item.Properties().group(group));
    }

    private static <B extends Block> B block(String id, B block) {
        block.setRegistryName(Midnight.resLoc(id));
        return block;
    }

    private static Block stone(String id, double hardness, double resistance, MaterialColor color) {
        return block(id, new Block(
            AbstractBlock.Properties.create(Material.ROCK, color)
                                    .sound(SoundType.STONE)
                                    .hardnessAndResistance((float) hardness, (float) resistance)
                                    .harvestTool(ToolType.PICKAXE)
        ));
    }

    private static Block dirt(String id, MaterialColor color) {
        return block(id, new NightDirtBlock(
            AbstractBlock.Properties.create(Material.EARTH, color)
                                    .sound(SoundType.GROUND)
                                    .hardnessAndResistance(0.5f)
                                    .harvestTool(ToolType.SHOVEL)
        ));
    }

    private static Block grassBlock(String id) {
        return block(id, new NightGrassBlock(
            AbstractBlock.Properties.create(Material.EARTH, MaterialColor.PURPLE_TERRACOTTA)
                                    .sound(SoundType.PLANT)
                                    .hardnessAndResistance(0.6f)
                                    .harvestTool(ToolType.SHOVEL)
                                    .tickRandomly()
        ));
    }

    private static Block mud(String id) {
        return block(id, new DeceitfulMudBlock(
            AbstractBlock.Properties.create(Material.EARTH, MaterialColor.BLUE_TERRACOTTA)
                                    .sound(MnSoundTypes.MUD)
                                    .hardnessAndResistance(0.5f)
                                    .harvestTool(ToolType.SHOVEL)
        ));
    }

    private static Block sand(String id) {
        return block(id, new StrangeSandBlock(
            AbstractBlock.Properties.create(Material.SAND, MaterialColor.BLUE_TERRACOTTA)
                                    .sound(SoundType.SAND)
                                    .hardnessAndResistance(0.5f)
                                    .harvestTool(ToolType.SHOVEL)
        ));
    }

    private static Block water(String id, Supplier<FlowingFluid> fluid) {
        return block(id, new FlowingFluidBlock(
            fluid,
            AbstractBlock.Properties.create(Material.WATER)
                                    .hardnessAndResistance(100)
        ));
    }

    private static MnPlantBlock plant(String id, double hardness, double resistance, Material material, MaterialColor color) {
        return block(id, new MnPlantBlock(
            AbstractBlock.Properties.create(material, color)
                                    .nonOpaque()
                                    .sound(SoundType.PLANT)
                                    .hardnessAndResistance((float) hardness, (float) resistance)
        ));
    }

    private static MnPlantBlock emissivePlant(String id, double hardness, double resistance, int emission, Material material, MaterialColor color) {
        return block(id, new MnPlantBlock(
            AbstractBlock.Properties.create(material, color)
                                    .nonOpaque()
                                    .sound(SoundType.PLANT)
                                    .luminance(state -> emission)
                                    .hardnessAndResistance((float) hardness, (float) resistance)
        ));
    }

    private static MnDoublePlantBlock tallPlant(String id, double hardness, double resistance, Material material, MaterialColor color) {
        return block(id, new MnDoublePlantBlock(
            AbstractBlock.Properties.create(material, color)
                                    .nonOpaque()
                                    .sound(SoundType.PLANT)
                                    .hardnessAndResistance((float) hardness, (float) resistance)
        ));
    }

    private static MnPlantBlock smallGrowable(String id, double hardness, double resistance, Material material, MaterialColor color, Supplier<MnDoublePlantBlock> tall) {
        return block(id, new SmallGrowablePlantBlock(
            AbstractBlock.Properties.create(material, color)
                                    .nonOpaque()
                                    .sound(SoundType.PLANT)
                                    .hardnessAndResistance((float) hardness, (float) resistance),
            tall
        ));
    }

    private static Block giantGhostPlant(String id, Function<Block.Properties, Block> factory) {
        return block(id, factory.apply(
            AbstractBlock.Properties.create(Material.WOOD, MaterialColor.SNOW)
                                    .nonOpaque()
                                    .sound(SoundType.NETHER_STEM)
                                    .hardnessAndResistance(0.3f)
                                    .luminance(state -> 15)
        ));
    }

    private static Block leaves(String id, MaterialColor color) {
        return block(id, new LeavesBlock(
            AbstractBlock.Properties.create(Material.LEAVES, color)
                                    .nonOpaque()
                                    .sound(SoundType.PLANT)
                                    .hardnessAndResistance(0.2f)
        ));
    }

    private static Block growsHangingLeaves(String id, MaterialColor color, Supplier<Block> hanging) {
        return block(id, new HangingLeavesGrowingBlock(
            AbstractBlock.Properties.create(Material.LEAVES, color)
                                    .nonOpaque()
                                    .sound(SoundType.PLANT)
                                    .hardnessAndResistance(0.2f),
            hanging
        ));
    }

    private static HangingLeavesBlock hangingLeaves(String id, MaterialColor color, Supplier<Block> leaves, ITag.INamedTag<Block> logs) {
        return block(id, new HangingLeavesBlock(
            AbstractBlock.Properties.create(Material.LEAVES, color)
                                    .nonOpaque()
                                    .sound(SoundType.CROP) // Make them sound a bit less leafier
                                    .hardnessAndResistance(0.1f),
            leaves, logs
        ));
    }

    private static Block log(String id, MaterialColor color, Supplier<Block> stripped) {
        return block(id, new StripableRotatedPillarBlock(
            AbstractBlock.Properties.create(Material.WOOD, color)
                                    .sound(SoundType.WOOD)
                                    .hardnessAndResistance(2f),
            stripped
        ));
    }

    private static Block log(String id, MaterialColor color) {
        return block(id, new RotatedPillarBlock(
            AbstractBlock.Properties.create(Material.WOOD, color)
                                    .sound(SoundType.WOOD)
                                    .hardnessAndResistance(2f)
        ));
    }

    private static Block wood(String id, MaterialColor color) {
        return block(id, new Block(
            AbstractBlock.Properties.create(Material.WOOD, color)
                                    .sound(SoundType.WOOD)
                                    .hardnessAndResistance(2f)
        ));
    }

    @Nonnull
    @SuppressWarnings("ConstantConditions")
    private static Block inj() {
        return null;
    }
}
