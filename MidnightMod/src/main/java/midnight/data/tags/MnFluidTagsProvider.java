package midnight.data.tags;

import midnight.common.block.fluid.MnFluids;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.TagsProvider;
import net.minecraft.fluid.Fluid;
import net.minecraft.tags.FluidTags;
import net.minecraft.tags.TagCollection;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;

import java.nio.file.Path;

public class MnFluidTagsProvider extends TagsProvider<Fluid> {
    @SuppressWarnings("deprecation") // We need Registry.FLUID. Sorry Forge...
    public MnFluidTagsProvider(DataGenerator gen) {
        super(gen, Registry.FLUID);
    }

    @Override
    protected void registerTags() {
        getBuilder(FluidTags.WATER).replace(false).add(MnFluids.DARK_WATER, MnFluids.FLOWING_DARK_WATER);
    }

    @Override
    protected void setCollection(TagCollection<Fluid> collection) {
        FluidTags.setCollection(collection);
    }

    @Override
    protected Path makePath(ResourceLocation id) {
        return generator.getOutputFolder().resolve("data/" + id.getNamespace() + "/tags/fluids/" + id.getPath() + ".json");
    }

    @Override
    public String getName() {
        return "MnFluidTags";
    }
}
