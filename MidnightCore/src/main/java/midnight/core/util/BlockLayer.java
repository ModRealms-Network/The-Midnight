package midnight.core.util;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;

/**
 * Replacement for the removed {@code BlockRenderLayer}. We use this in block builders to dynamically set the block
 * render layer and delegate that to the {@link RenderTypeLookup} when available.
 */
public enum BlockLayer {
    SOLID,
    CUTOUT,
    CUTOUT_MIPPED,
    TRANSLUCENT,
    TRIPWIRE;

    /**
     * Returns the corresponding {@link RenderType} for this render layer.
     */
    @OnlyIn(Dist.CLIENT)
    public RenderType getRenderType() {
        switch (this) {
            default:
            case SOLID:
                return RenderType.getSolid();
            case CUTOUT:
                return RenderType.getCutout();
            case CUTOUT_MIPPED:
                return RenderType.getCutoutMipped();
            case TRANSLUCENT:
                return RenderType.getTranslucent();
            case TRIPWIRE:
                return RenderType.func_241715_r_(); // TODO There doesn't seem to be a getTripwire() method in the RenderType class. Is it called something different in 1.15.2?
        }
    }

    /**
     * Returns the render layer corresponding to the specified {@link RenderType}, or {@link #SOLID} if not found.
     */
    @OnlyIn(Dist.CLIENT)
    public static BlockLayer getFromRenderType(RenderType type) {
        if (type == RenderType.getSolid()) return SOLID;
        if (type == RenderType.getCutout()) return CUTOUT;
        if (type == RenderType.getCutoutMipped()) return CUTOUT_MIPPED;
        if (type == RenderType.getTranslucent()) return TRANSLUCENT;
        if (type == RenderType.func_241715_r_()) return TRIPWIRE;
        return SOLID;
    }
}
