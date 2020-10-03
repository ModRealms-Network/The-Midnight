package midnight.common.world.biome;

//public class MnBiomeProvider extends BiomeProvider {
//    private final FractalGenerator<Biome> generator;
//
//    public MnBiomeProvider(WorldInfo info) {
//        super(Sets.newHashSet(
//            MnBiomes.DECEITFUL_BOG,
//            MnBiomes.NIGHT_PLAINS,
//            MnBiomes.VIGILANT_FOREST
//        ));
//
//        CachingRegionContext ctx = new CachingRegionContext(25, info.getSeed());
//        generator = ctx.generate(new MidnightBiomeLayer(), 8812L)
//                       .zoomFuzzy()
//                       .zoom(5)
//                       .smooth()
//                       .makeGenerator(region -> new FractalGenerator<Biome>(region) {
//                           @Override
//                           protected Biome toValue(int id) {
//                               return MnBiomes.biome(id);
//                           }
//
//                           @Override
//                           protected Biome[] createArray(int size) {
//                               return new Biome[size];
//                           }
//                       });
//    }
//
//    @Override
//    public Biome getNoiseBiome(int x, int y, int z) {
//        return generator.generate(x, z);
//    }
//}
