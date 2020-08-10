package midnight.common.registry;

import com.google.common.collect.Sets;
import com.mojang.datafixers.util.Pair;
import midnight.common.Midnight;
import midnight.common.block.color.IColoredBlock;
import midnight.common.item.IColoredItem;
import midnight.core.util.BlockLayer;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.item.BlockItem;
import net.minecraft.item.DyeColor;
import net.minecraft.item.Item;
import net.minecraftforge.common.ToolType;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * Block builder class to wrap around {@link Block.Properties} for clarity and to configure and create items with the
 * configured block.
 */
public class BlockItemBuilder<B extends Block> extends AbstractItemBuilder<BlockItemBuilder<B>, BlockItem> {
    private Function<? super Block.Properties, ? extends B> factory;
    private BiFunction<? super B, ? super Item.Properties, ? extends BlockItem> itemFactory = BlockItem::new;
    private Material material = Material.ROCK;
    private MaterialColor color = null;
    private boolean blocksMovement = true;
    private SoundType sound = SoundType.STONE;
    private int lightLevel = 0;
    private double resistance = 0;
    private double hardness = 0;
    private boolean ticksRandomly = false;
    private double slipperiness = 0.6;
    private double speedFactor = 1;
    private double jumpFactor = 1;
    private boolean drops = true;
    private boolean solid = true;
    private int harvestLevel = -1;
    private ToolType harvestTool = null;
    private Block lootFrom;
    private boolean variableOpacity;

    private final List<Consumer<? super B>> blockProcess = new ArrayList<>();

    private BlockItemBuilder(Function<? super Block.Properties, ? extends B> factory) {
        this.factory = factory;
    }

    /**
     * Set the factory that creates the block. The factory must not be null. If you create a builder yourself prefer
     * specifying your factory directly to {@link #builder}.
     */
    public BlockItemBuilder<B> factory(Function<? super Block.Properties, ? extends B> factory) {
        this.factory = factory;
        return this;
    }

    /**
     * Set the {@link Material} of the block. Default is {@link Material#ROCK}.
     */
    public BlockItemBuilder<B> material(Material material) {
        this.material = material;
        return this;
    }

    /**
     * Set the {@link MaterialColor} of the block. Default depends on material.
     */
    public BlockItemBuilder<B> color(MaterialColor color) {
        this.color = color;
        return this;
    }

    /**
     * Set the {@link MaterialColor} of the block by taking it from the specified {@link DyeColor}.
     */
    public BlockItemBuilder<B> color(DyeColor color) {
        this.color = color.getMapColor();
        return this;
    }

    /**
     * Sets whether the block disables entities from moving through it. Default is true.
     */
    public BlockItemBuilder<B> blocksMovement(boolean blocksMovement) {
        this.blocksMovement = blocksMovement;
        return this;
    }

    /**
     * Sets the sound type of this block. Default is {@link SoundType#STONE}.
     */
    public BlockItemBuilder<B> sound(SoundType sound) {
        this.sound = sound;
        return this;
    }

    /**
     * Sets how much light the block emits. Default is 0.
     */
    public BlockItemBuilder<B> emission(int light) {
        this.lightLevel = light;
        return this;
    }

    /**
     * Sets the mining hardness of the block. Default is 0.
     */
    public BlockItemBuilder<B> hardness(double hardness) {
        this.hardness = hardness;
        return this;
    }

    /**
     * Sets the explosion resistance of the block. Default is 0.
     */
    public BlockItemBuilder<B> resistance(double resistance) {
        this.resistance = resistance;
        return this;
    }

    /**
     * Sets the mining hardness and the explosion resistance (together: the strength) of the block to two separate
     * values.
     */
    public BlockItemBuilder<B> strength(double hardness, double resistance) {
        this.hardness = hardness;
        this.resistance = resistance;
        return this;
    }

    /**
     * Sets the mining hardness and the explosion resistance (together: the strength) of the block to the same value.
     */
    public BlockItemBuilder<B> strength(double hardness) {
        this.hardness = hardness;
        this.resistance = hardness;
        return this;
    }

    /**
     * Makes the block insta-mineable.
     */
    public BlockItemBuilder<B> noStrength() {
        this.hardness = 0;
        this.resistance = 0;
        return this;
    }

    /**
     * Makes the block immortal, unbreakable, like bedrock.
     */
    public BlockItemBuilder<B> unbreakable() {
        this.hardness = -1;
        this.resistance = 36000000;
        return this;
    }

    /**
     * Sets whether this block must receive random ticks. Default is false.
     */
    public BlockItemBuilder<B> ticksRandomly(boolean tick) {
        this.ticksRandomly = tick;
        return this;
    }

    /**
     * Sets the slipperiness of the block. Default is 0.6.
     */
    public BlockItemBuilder<B> slipperiness(double slipperiness) {
        this.slipperiness = slipperiness;
        return this;
    }

    /**
     * Sets the speed multiplier of the block. Default is 1.
     */
    public BlockItemBuilder<B> speedFactor(double speedFactor) {
        this.speedFactor = speedFactor;
        return this;
    }

    /**
     * Sets the jump height multiplier of the block. Default is 1.
     */
    public BlockItemBuilder<B> jumpFactor(double jumpFactor) {
        this.jumpFactor = jumpFactor;
        return this;
    }

    /**
     * Sets whether this block drops any items. Default is true.
     */
    public BlockItemBuilder<B> drops(boolean drops) {
        this.drops = drops;
        return this;
    }

    /**
     * Sets whether this block is a solid and fully opaque block. Default is true.
     */
    public BlockItemBuilder<B> solid(boolean solid) {
        this.solid = solid;
        return this;
    }

    /**
     * Sets the required harvest tool of this block. Default is null.
     */
    public BlockItemBuilder<B> harvestTool(ToolType tool) {
        this.harvestTool = tool;
        return this;
    }

    /**
     * Sets the required harvest tool tier of this block. Default is -1.
     */
    public BlockItemBuilder<B> harvestLevel(int level) {
        this.harvestLevel = level;
        return this;
    }

    /**
     * Sets the required harvest tool and its required tier of this block.
     */
    public BlockItemBuilder<B> harvestTool(ToolType tool, int level) {
        this.harvestTool = tool;
        this.harvestLevel = level;
        return this;
    }

    /**
     * Sets the drops of this block to be equal that of another block.
     */
    public BlockItemBuilder<B> lootFrom(Block block) {
        this.lootFrom = block;
        return this;
    }

    /**
     * Sets whether this block has variable opacity. Default is false.
     */
    public BlockItemBuilder<B> variableOpacity(boolean variableOpac) {
        this.variableOpacity = variableOpac;
        return this;
    }

    /**
     * Sets an item factory for this block.
     */
    public BlockItemBuilder<B> itemFactory(BiFunction<? super B, ? super Item.Properties, ? extends BlockItem> factory) {
        this.itemFactory = factory;
        return this;
    }

    /**
     * Adds a post-processor to process the block right before creation.
     */
    public BlockItemBuilder<B> processBlock(Consumer<? super B> processor) {
        this.blockProcess.add(processor);
        return this;
    }

    /**
     * Sets the render type of this block. Delegates to the client via a proxy.
     */
    public BlockItemBuilder<B> renderLayer(BlockLayer renderType) {
        processBlock(block -> Midnight.get().getBlockItemProxy().registerRenderLayer(block, renderType));
        return this;
    }

    /**
     * Sets the render type predicate of this block. Delegates to the client via a proxy.
     */
    public BlockItemBuilder<B> renderLayer(Predicate<BlockLayer> renderType) {
        processBlock(block -> Midnight.get().getBlockItemProxy().registerRenderLayer(block, renderType));
        return this;
    }

    /**
     * Sets the render types of this block. Delegates to the client via a proxy.
     */
    public BlockItemBuilder<B> renderLayer(BlockLayer... renderTypes) {
        Set<BlockLayer> layers = Sets.newHashSet(renderTypes);
        processBlock(block -> Midnight.get().getBlockItemProxy().registerRenderLayer(block, layers::contains));
        return this;
    }

    /**
     * Sets the block coloring of this block. Delegates to the client via a proxy.
     */
    public BlockItemBuilder<B> multiplier(IColoredBlock color) {
        processBlock(block -> Midnight.get().getBlockItemProxy().registerColoredBlock(block, color));
        processItem(item -> Midnight.get().getBlockItemProxy().registerColoredItem(item, color));
        return this;
    }

    /**
     * Sets the block coloring of the item of this block. Delegates to the client via a proxy.
     *
     * @deprecated Do not use: use {@link #multiplier(IColoredBlock)} for blocks instead.
     */
    @Deprecated
    @Override
    public BlockItemBuilder<B> multiplier(IColoredItem color) {
        return super.multiplier(color);
    }

    /**
     * Makes the {@link Block.Properties} instance using the configuration in this builder.
     */
    protected Block.Properties makeBlockProps() {
        Block.Properties props = Block.Properties.create(material, color);
        if(!blocksMovement) props.doesNotBlockMovement();
        props.sound(sound);
        props.lightValue(lightLevel);
        props.hardnessAndResistance((float) hardness, (float) resistance);
        if(ticksRandomly) props.tickRandomly();
        props.slipperiness((float) slipperiness);
        props.speedFactor((float) speedFactor);
        props.jumpFactor((float) jumpFactor);
        if(!drops) props.noDrops();
        if(!solid) props.notSolid();
        props.harvestLevel(harvestLevel);
        props.harvestTool(harvestTool);
        if(drops && lootFrom != null) props.lootFrom(lootFrom);
        if(variableOpacity) props.variableOpacity();
        return props;
    }

    /**
     * Makes and post-processes a configured block.
     */
    public B makeBlock() {
        B block = factory.apply(makeBlockProps());
        for(Consumer<? super B> processor : blockProcess) {
            processor.accept(block);
        }
        return block;
    }

    /**
     * Makes and post-processes an item for the configured block.
     */
    public BlockItem makeItem(B block) {
        BlockItem item = itemFactory.apply(block, makeItemProps());
        for(Consumer<? super BlockItem> processor : itemProcess) {
            processor.accept(item);
        }
        return item;
    }

    /**
     * Makes a pair of a configured block and its respective item.
     */
    public Pair<B, BlockItem> makeBlockAndItem() {
        B block = makeBlock();
        BlockItem item = makeItem(block);
        return Pair.of(block, item);
    }

    /**
     * Creates a builder for the given block factory. The block factory may not be null.
     */
    public static <B extends Block> BlockItemBuilder<B> builder(Function<? super Block.Properties, ? extends B> factory) {
        return new BlockItemBuilder<>(factory);
    }
}
