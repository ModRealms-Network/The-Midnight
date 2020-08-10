package midnight.common.world.levelgen.surface;

import net.minecraft.world.gen.surfacebuilders.SurfaceBuilderConfig;

public class MnSurfaceBuilders {
    public static final DeceitfulBogSurfaceBuilder DECEITFUL_BOG = new DeceitfulBogSurfaceBuilder(SurfaceBuilderConfig::deserialize);
}
