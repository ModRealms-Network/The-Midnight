package midnight.data.models;

import net.minecraft.util.ResourceLocation;
import midnight.common.Midnight;

import java.util.function.BiConsumer;

public abstract class ModelTable {
    public abstract void collectModels(BiConsumer<ResourceLocation, IModelGen> consumer);

    public static void add(BiConsumer<ResourceLocation, IModelGen> consumer, String id, IModelGen gen) {
        consumer.accept(Midnight.resLoc(id), gen);
    }
}
