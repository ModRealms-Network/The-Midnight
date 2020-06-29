package midnight.common.registry;

import com.mojang.datafixers.util.Pair;
import net.minecraftforge.common.ToolType;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.entity.EntityType;
import net.minecraft.item.BlockItem;
import net.minecraft.item.DyeColor;
import net.minecraft.item.Item;
import net.minecraft.util.Direction;

import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.ToIntFunction;

public class BlockItemBuilder<B extends Block> extends AbstractItemBuilder<BlockItemBuilder<B>> {
    private final Function<? super AbstractBlock.Properties, ? extends B> factory;
    private BiFunction<? super B, ? super Item.Properties, ? extends BlockItem> itemFactory = BlockItem::new;
    private Material material = Material.ROCK;
    private Function<BlockState, MaterialColor> color = state -> state.getMaterial().getColor();
    private boolean blocksMovement = true;
    private SoundType sound = SoundType.STONE;
    private ToIntFunction<BlockState> lightLevel = state -> 0;
    private double resistance = 0;
    private double hardness = 0;
    private boolean requiresTool = false;
    private boolean ticksRandomly = false;
    private double slipperiness = 0.6;
    private double speedFactor = 1;
    private double jumpFactor = 1;
    private boolean drops = true;
    private boolean solid = true;
    private boolean air = false;
    private int harvestLevel = -1;
    private ToolType harvestTool = null;
    private Block lootFrom;
    private AbstractBlock.IExtendedPositionPredicate<EntityType<?>> canEntitySpawn // func_235827_a_
            = (state, world, pos, type) -> state.isSolidSide(world, pos, Direction.UP) && state.getLightValue() < 14;
    private AbstractBlock.IPositionPredicate normalCube // func_235828_a_
            = (state, world, pos) -> state.getMaterial().isOpaque() && state.func_235785_r_(world, pos);
    private AbstractBlock.IPositionPredicate suffocation // func_235842_b_
            = (state, world, pos) -> state.getMaterial().blocksMovement() && state.func_235785_r_(world, pos);
    private AbstractBlock.IPositionPredicate blockOverlay // func_235847_c_
            = suffocation;
    private AbstractBlock.IPositionPredicate postProcessing // func_235852_d_
            = (state, world, pos) -> false;
    private AbstractBlock.IPositionPredicate emissiveRendering // func_235856_e_
            = (state, world, pos) -> false;
    private boolean variableOpacity;

    private BlockItemBuilder(Function<? super AbstractBlock.Properties, ? extends B> factory) {
        this.factory = factory;
    }

    public BlockItemBuilder<B> material(Material material) {
        this.material = material;
        return this;
    }

    public BlockItemBuilder<B> color(MaterialColor color) {
        this.color = state -> color;
        return this;
    }

    public BlockItemBuilder<B> color(DyeColor color) {
        this.color = state -> color.getMapColor();
        return this;
    }

    public BlockItemBuilder<B> color(Function<BlockState, MaterialColor> color) {
        this.color = color;
        return this;
    }

    public BlockItemBuilder<B> blocksMovement(boolean blocksMovement) {
        this.blocksMovement = blocksMovement;
        return this;
    }

    public BlockItemBuilder<B> sound(SoundType sound) {
        this.sound = sound;
        return this;
    }

    public BlockItemBuilder<B> emission(int light) {
        this.lightLevel = state -> light;
        return this;
    }

    public BlockItemBuilder<B> emission(ToIntFunction<BlockState> light) {
        this.lightLevel = light;
        return this;
    }

    public BlockItemBuilder<B> hardness(double hardness) {
        this.hardness = hardness;
        return this;
    }

    public BlockItemBuilder<B> resistance(double resistance) {
        this.resistance = resistance;
        return this;
    }

    public BlockItemBuilder<B> strength(double hardness, double resistance) {
        this.hardness = hardness;
        this.resistance = resistance;
        return this;
    }

    public BlockItemBuilder<B> strength(double hardness) {
        this.hardness = hardness;
        this.resistance = hardness;
        return this;
    }

    public BlockItemBuilder<B> noStrength() {
        this.hardness = 0;
        this.resistance = 0;
        return this;
    }

    public BlockItemBuilder<B> unbreakable() {
        this.hardness = -1;
        this.resistance = 36000000;
        return this;
    }

    public BlockItemBuilder<B> requiresTool(boolean require) {
        this.requiresTool = require;
        return this;
    }

    public BlockItemBuilder<B> ticksRandomly(boolean tick) {
        this.ticksRandomly = tick;
        return this;
    }

    public BlockItemBuilder<B> slipperiness(double slipperiness) {
        this.slipperiness = slipperiness;
        return this;
    }

    public BlockItemBuilder<B> speedFactor(double speedFactor) {
        this.speedFactor = speedFactor;
        return this;
    }

    public BlockItemBuilder<B> jumpFactor(double jumpFactor) {
        this.jumpFactor = jumpFactor;
        return this;
    }

    public BlockItemBuilder<B> drops(boolean drops) {
        this.drops = drops;
        return this;
    }

    public BlockItemBuilder<B> solid(boolean solid) {
        this.solid = solid;
        return this;
    }

    public BlockItemBuilder<B> air(boolean air) {
        this.air = air;
        return this;
    }

    public BlockItemBuilder<B> harvestTool(ToolType tool) {
        this.harvestTool = tool;
        return this;
    }

    public BlockItemBuilder<B> harvestLevel(int level) {
        this.harvestLevel = level;
        return this;
    }

    public BlockItemBuilder<B> harvestTool(ToolType tool, int level) {
        this.harvestTool = tool;
        this.harvestLevel = level;
        return this;
    }

    public BlockItemBuilder<B> lootFrom(Block block) {
        this.lootFrom = block;
        return this;
    }

    public BlockItemBuilder<B> canEntitySpawn(AbstractBlock.IExtendedPositionPredicate<EntityType<?>> pred) {
        this.canEntitySpawn = pred;
        return this;
    }

    public BlockItemBuilder<B> canEntitySpawn(boolean canSpawn) {
        this.canEntitySpawn = (s, w, p, t) -> canSpawn;
        return this;
    }

    public BlockItemBuilder<B> normalCube(AbstractBlock.IPositionPredicate pred) {
        this.normalCube = pred;
        return this;
    }

    public BlockItemBuilder<B> normalCube(boolean normal) {
        this.normalCube = (s, w, p) -> normal;
        return this;
    }

    public BlockItemBuilder<B> suffocation(AbstractBlock.IPositionPredicate pred) {
        this.suffocation = pred;
        return this;
    }

    public BlockItemBuilder<B> suffocation(boolean suffocation) {
        this.suffocation = (s, w, p) -> suffocation;
        return this;
    }

    public BlockItemBuilder<B> blockOverlay(AbstractBlock.IPositionPredicate pred) {
        this.blockOverlay = pred;
        return this;
    }

    public BlockItemBuilder<B> blockOverlay(boolean overlay) {
        this.blockOverlay = (s, w, p) -> overlay;
        return this;
    }

    public BlockItemBuilder<B> postProcess(AbstractBlock.IPositionPredicate pred) {
        this.postProcessing = pred;
        return this;
    }

    public BlockItemBuilder<B> postProcess(boolean process) {
        this.postProcessing = (s, w, p) -> process;
        return this;
    }

    public BlockItemBuilder<B> emissive(AbstractBlock.IPositionPredicate pred) {
        this.emissiveRendering = pred;
        return this;
    }

    public BlockItemBuilder<B> emissive(boolean emissive) {
        this.emissiveRendering = (s, w, p) -> emissive;
        return this;
    }

    public BlockItemBuilder<B> variableOpacity(boolean variableOpac) {
        this.variableOpacity = variableOpac;
        return this;
    }

    public BlockItemBuilder<B> itemFactory(BiFunction<? super B, ? super Item.Properties, ? extends BlockItem> factory) {
        this.itemFactory = factory;
        return this;
    }

    protected AbstractBlock.Properties makeBlockProps() {
        AbstractBlock.Properties props = AbstractBlock.Properties.func_235836_a_(material, color);
        if (!blocksMovement) props.doesNotBlockMovement();
        props.sound(sound);
        props.func_235838_a_(lightLevel);
        props.hardnessAndResistance((float) hardness, (float) resistance);
        if (requiresTool) props.func_235861_h_();
        if (ticksRandomly) props.tickRandomly();
        props.slipperiness((float) slipperiness);
        props.speedFactor((float) speedFactor);
        props.jumpFactor((float) jumpFactor);
        if (!drops) props.noDrops();
        if (!solid) props.notSolid();
        if (air) props.func_235859_g_();
        props.harvestLevel(harvestLevel);
        props.harvestTool(harvestTool);
        if (drops && lootFrom != null) props.lootFrom(lootFrom);
        props.func_235827_a_(canEntitySpawn);
        props.func_235828_a_(normalCube);
        props.func_235842_b_(suffocation);
        props.func_235847_c_(blockOverlay);
        props.func_235852_d_(postProcessing);
        props.func_235856_e_(emissiveRendering);
        if (variableOpacity) props.variableOpacity();
        return props;
    }

    public B makeBlock() {
        return factory.apply(makeBlockProps());
    }

    public BlockItem makeItem(B block) {
        return itemFactory.apply(block, makeItemProps());
    }

    public Pair<B, BlockItem> makeBlockAndItem() {
        B block = makeBlock();
        BlockItem item = makeItem(block);
        return Pair.of(block, item);
    }

    public static <B extends Block> BlockItemBuilder<B> builder(Function<? super AbstractBlock.Properties, ? extends B> factory) {
        return new BlockItemBuilder<>(factory);
    }
}
