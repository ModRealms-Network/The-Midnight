package midnight.core.util;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import net.minecraft.client.renderer.RenderType;

public enum BlockLayer {
    SOLID,
    CUTOUT,
    CUTOUT_MIPPED,
    TRANSLUCENT,
    TRIPWIRE;

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
                return RenderType.func_241715_r_();
        }
    }

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
