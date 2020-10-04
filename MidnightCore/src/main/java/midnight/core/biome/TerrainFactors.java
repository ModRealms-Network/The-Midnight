package midnight.core.biome;


import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

public class TerrainFactors {
    public static final Codec<TerrainFactors> CODEC = RecordCodecBuilder.create(
        instance -> instance.group(
            Codec.DOUBLE.fieldOf("height")
                        .orElse(0D)
                        .forGetter(TerrainFactors::getHeight),
            Codec.DOUBLE.fieldOf("difference")
                        .orElse(1D)
                        .forGetter(TerrainFactors::getDifference),
            Codec.DOUBLE.fieldOf("hilliness")
                        .orElse(1D)
                        .forGetter(TerrainFactors::getHilliness),
            Codec.DOUBLE.fieldOf("granularity")
                        .orElse(0.4)
                        .forGetter(TerrainFactors::getGranularity)
        ).apply(instance, instance.stable(TerrainFactors::new))
    );

    private final double height;
    private final double difference;
    private final double hilliness;
    private final double granularity;

    private TerrainFactors(double height, double difference, double hilliness, double granularity) {
        this.height = height;
        this.difference = difference;
        this.hilliness = hilliness;
        this.granularity = granularity;
    }

    public double getHeight() {
        return height;
    }

    public double getDifference() {
        return difference;
    }

    public double getHilliness() {
        return hilliness;
    }

    public double getGranularity() {
        return granularity;
    }

    public static class Builder {
        private double height = 0;
        private double difference = 1;
        private double hilliness = 1;
        private double granularity = 0.4;

        public Builder height(double height) {
            this.height = height;
            return this;
        }

        public Builder difference(double difference) {
            this.difference = difference;
            return this;
        }

        public Builder hilliness(double hilliness) {
            this.hilliness = hilliness;
            return this;
        }

        public Builder granularity(double granularity) {
            this.granularity = granularity;
            return this;
        }

        public TerrainFactors build() {
            return new TerrainFactors(height, difference, hilliness, granularity);
        }
    }
}
