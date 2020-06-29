package midnight.common.registry;

import com.google.common.collect.Maps;
import net.minecraftforge.common.ToolType;

import net.minecraft.client.renderer.tileentity.ItemStackTileEntityRenderer;
import net.minecraft.item.Food;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.Rarity;

import java.util.Map;
import java.util.concurrent.Callable;
import java.util.function.Supplier;

@SuppressWarnings("unchecked")
public abstract class AbstractItemBuilder<T extends AbstractItemBuilder<T>> {
    protected int stackSize = 64;
    protected int durability = 0;
    protected Item container = null;
    protected ItemGroup group = null;
    protected Rarity rarity = Rarity.COMMON;
    protected Food food = null;
    protected boolean resistsFire = false;
    protected boolean canRepair = false;
    private Map<ToolType, Integer> toolClasses = Maps.newHashMap();
    private Supplier<Callable<ItemStackTileEntityRenderer>> teRenderer = null;

    public T maxStackSize(int size) {
        stackSize = size;
        return (T) this;
    }

    public T maxDurability(int max) {
        durability = max;
        return (T) this;
    }

    public T container(Item container) {
        this.container = container;
        return (T) this;
    }

    public T group(ItemGroup group) {
        this.group = group;
        return (T) this;
    }

    public T rarity(Rarity rarity) {
        this.rarity = rarity;
        return (T) this;
    }

    public T food(Food food) {
        this.food = food;
        return (T) this;
    }

    public T resistsFire(boolean resistsFire) {
        this.resistsFire = resistsFire;
        return (T) this;
    }

    public T canRepair(boolean canRepair) {
        this.canRepair = canRepair;
        return (T) this;
    }

    public T tool(ToolType type, int tier) {
        this.toolClasses.put(type, tier);
        return (T) this;
    }

    public T teRenderer(Supplier<Callable<ItemStackTileEntityRenderer>> teRendererCallableSupplier) {
        teRenderer = teRendererCallableSupplier;
        return (T) this;
    }

    protected Item.Properties makeItemProps() {
        Item.Properties props = new Item.Properties();
        props.maxStackSize(stackSize);
        props.maxDamage(durability);
        props.containerItem(container);
        props.group(group);
        props.rarity(rarity);
        props.food(food);
        if (resistsFire) props.func_234689_a_();
        if (!canRepair) props.setNoRepair();
        for (Map.Entry<ToolType, Integer> toolTier : toolClasses.entrySet()) {
            props.addToolType(toolTier.getKey(), toolTier.getValue());
        }
        props.setISTER(teRenderer);
        return props;
    }
}
