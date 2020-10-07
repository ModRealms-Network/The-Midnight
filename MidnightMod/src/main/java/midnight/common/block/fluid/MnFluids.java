package midnight.common.block.fluid;

import midnight.common.Midnight;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraft.fluid.FlowingFluid;
import net.minecraft.fluid.Fluid;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.ObjectHolder;

import javax.annotation.Nonnull;

/**
 * This class registers and stores the list of Midnight fluids and their respective blocks.
 *
 * @author Shadew
 * @version 0.6.0
 * @since 0.6.0
 */
@ObjectHolder("midnight")
public final class MnFluids {
    public static final FlowingFluid DARK_WATER = inj();
    public static final FlowingFluid FLOWING_DARK_WATER = inj();


    public static void registerFluids(IForgeRegistry<Fluid> registry) {
        registry.registerAll(
            fluid("dark_water", new DarkWaterFluid.Source()),
            fluid("flowing_dark_water", new DarkWaterFluid.Flowing())
        );
    }

    @OnlyIn(Dist.CLIENT)
    public static void setupRenderers() {
        RenderTypeLookup.setRenderLayer(DARK_WATER, RenderType.getTranslucent());
        RenderTypeLookup.setRenderLayer(FLOWING_DARK_WATER, RenderType.getTranslucent());
    }

    private MnFluids() {
    }

    private static <F extends Fluid> F fluid(String id, F fluid) {
        fluid.setRegistryName(Midnight.resLoc(id));
        return fluid;
    }

    @Nonnull
    @SuppressWarnings("ConstantConditions")
    private static <F extends Fluid> F inj() {
        return null;
    }
}
