package midnight.common.misc;

import midnight.client.particle.SporeParticle;
import midnight.common.Midnight;
import midnight.core.util.IRegistry;
import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.ParticleManager;
import net.minecraft.particles.BasicParticleType;
import net.minecraft.particles.ParticleType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.ArrayList;

public final class MnParticleTypes {
    private static final ArrayList<ParticleType<?>> REGISTRY = new ArrayList<>();

    public static final BasicParticleType SPORE = type("spore", new BasicParticleType(false));

    private MnParticleTypes() {
    }

    @OnlyIn(Dist.CLIENT)
    public static void setupClient() {
        ParticleManager particles = Minecraft.getInstance().particles;
        particles.registerFactory(SPORE, SporeParticle.Factory::new);
    }

    public static void registerParticleTypes(IRegistry<ParticleType<?>> registry) {
        REGISTRY.forEach(registry::register);
    }

    private static <T extends ParticleType<?>> T type(String id, T type) {
        type.setRegistryName(Midnight.resLoc(id));
        REGISTRY.add(type);
        return type;
    }
}
