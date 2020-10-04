package midnight.core.biome;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

public class BiomeColoring {
    public static final Codec<BiomeColoring> CODEC = RecordCodecBuilder.create(
        instance -> instance.group(
            Codec.INT.fieldOf("grass")
                     .orElse(0x506C78)
                     .forGetter(BiomeColoring::getGrassColor),
            Codec.INT.fieldOf("water")
                     .orElse(0x22517D)
                     .forGetter(BiomeColoring::getWaterColor)
        ).apply(instance, instance.stable(BiomeColoring::new))
    );

    private final int grassColor;
    private final int waterColor;

    private BiomeColoring(int grassColor, int waterColor) {
        this.grassColor = grassColor;
        this.waterColor = waterColor;
    }

    public int getGrassColor() {
        return grassColor;
    }

    public int getWaterColor() {
        return waterColor;
    }

    public static class Builder {
        private int grassColor = 0x506C78;
        private int waterColor = 0x22517D;

        public Builder grassColor(int grassColor) {
            this.grassColor = grassColor;
            return this;
        }

        public Builder waterColor(int waterColor) {
            this.waterColor = waterColor;
            return this;
        }

        public BiomeColoring build() {
            return new BiomeColoring(grassColor, waterColor);
        }
    }
}
