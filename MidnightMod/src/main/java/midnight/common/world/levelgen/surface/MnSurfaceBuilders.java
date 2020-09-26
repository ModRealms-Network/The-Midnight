package midnight.common.world.levelgen.surface;

import net.minecraft.world.gen.surfacebuilders.SurfaceBuilderConfig;

public final class MnSurfaceBuilders {
    public static final DeceitfulBogSurfaceBuilder DECEITFUL_BOG = new DeceitfulBogSurfaceBuilder(SurfaceBuilderConfig::deserialize);

    private MnSurfaceBuilders() {
    }
}
