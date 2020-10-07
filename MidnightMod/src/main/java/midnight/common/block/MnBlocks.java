package midnight.common.block;

import midnight.client.MidnightClient;
import midnight.common.Midnight;
import midnight.common.block.fluid.MnFluids;
import midnight.common.item.MnItemGroups;
import midnight.common.world.biome.MnBiomeColors;
import midnight.core.util.ColorUtil;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.FlowingFluidBlock;
import net.minecraft.block.SoundType;
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
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.ObjectHolder;

import javax.annotation.Nonnull;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * This class holds all the blocks for the midnight.
 *
 * @author Shadew
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

    public static void registerBlocks(IForgeRegistry<Block> registry) {
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

            smallGrowable("night_grass", 0, 0, Material.TALL_PLANTS, MaterialColor.PURPLE_TERRACOTTA, () -> (MnDoublePlantBlock) TALL_NIGHT_GRASS).setPlantHitbox(14, 14).setOffsetType(Block.OffsetType.XYZ),
            tallPlant("tall_night_grass", 0, 0, Material.TALL_PLANTS, MaterialColor.PURPLE_TERRACOTTA).setPlantHitbox(14, 14).setOffsetType(Block.OffsetType.XYZ),

            giantGhostPlant("ghost_plant_stem", GhostPlantStemBlock::new),
            giantGhostPlant("ghost_plant_leaf", GhostPlantBlock::new),
            emissivePlant("ghost_plant", 0, 0, 9, Material.PLANTS, MaterialColor.SNOW).setPlantHitbox(14, 14).setOffsetType(Block.OffsetType.XZ)
        );
    }

    public static void registerItems(IForgeRegistry<Item> registry) {
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
            item(GHOST_PLANT, MnItemGroups.DECOR)
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
                                    .sound(SoundType.WOOD)
                                    .hardnessAndResistance(0.3f)
                                    .luminance(state -> 15)
        ));
    }

    @Nonnull
    @SuppressWarnings("ConstantConditions")
    private static Block inj() {
        return null;
    }
}
