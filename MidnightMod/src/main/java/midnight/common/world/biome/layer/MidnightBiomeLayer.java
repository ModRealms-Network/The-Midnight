package midnight.common.world.biome.layer;

import net.shadew.ptg.region.RegionRNG;
import net.shadew.ptg.region.layer.GeneratorLayer;

public class MidnightBiomeLayer implements GeneratorLayer {
    private final int[] biomes;
    private final int[] weights;

    private final int totalWeight;

    public MidnightBiomeLayer(int[] biomes, int[] weights) {
        this.biomes = biomes;
        this.weights = weights;
        int wgt = 0;
        for(int i : weights) wgt += i;
        totalWeight = wgt;
    }

    @Override
    public int generate(RegionRNG rng, int x, int z) {
        int rand = rng.random(totalWeight);
        int i = 0;
        for(int w : weights) {
            rand -= w;
            if(rand <= 0) {
                return biomes[i];
            }
            i++;
        }
        return biomes[0];
    }
}
