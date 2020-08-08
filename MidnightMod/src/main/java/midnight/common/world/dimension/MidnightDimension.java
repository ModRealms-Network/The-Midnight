package midnight.common.world.dimension;

import midnight.common.world.biome.MnBiomes;
import midnight.common.world.levelgen.midnight.MidnightChunkGenerator;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraft.world.biome.provider.SingleBiomeProvider;
import net.minecraft.world.biome.provider.SingleBiomeProviderSettings;
import net.minecraft.world.dimension.Dimension;
import net.minecraft.world.dimension.DimensionType;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.GenerationSettings;

public class MidnightDimension extends Dimension {
    public MidnightDimension(World world, DimensionType type) {
        super(world, type, 0);
    }

    @Override
    public ChunkGenerator<?> createChunkGenerator() {
        return new MidnightChunkGenerator(world, new SingleBiomeProvider(new SingleBiomeProviderSettings(world.getWorldInfo()).setBiome(MnBiomes.NIGHT_PLAINS)), new GenerationSettings());
    }

    @Override
    public BlockPos findSpawn(ChunkPos chunkPosIn, boolean checkValid) {
        return null;
    }

    @Override
    public BlockPos findSpawn(int posX, int posZ, boolean checkValid) {
        return null;
    }

    @Override
    public float calculateCelestialAngle(long worldTime, float partialTicks) {
        return 0;
    }

    @Override
    public boolean isSurfaceWorld() {
        return false;
    }

    @Override
    public Vec3d getFogColor(float celestialAngle, float partialTicks) {
        return new Vec3d(0, 0, 0);
    }

    @Override
    public boolean canRespawnHere() {
        return false;
    }

    @Override
    public boolean doesXZShowFog(int x, int z) {
        return false;
    }
}
