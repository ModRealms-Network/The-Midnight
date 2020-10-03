package midnight;

import midnight.client.MidnightClient;
import midnight.common.Midnight;
import midnight.server.MidnightServer;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLLoadCompleteEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.loading.FMLEnvironment;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Bootstrap class of the Midnight. This is the first class of the Midnight that is being loaded by Forge. From here we
 * start interfacing with Minecraft.
 */
@Mod(MidnightInfo.MODID)
public class MidnightMod {
    private static final Logger LOGGER = LogManager.getLogger("Midnight Mod");

    /**
     * The general {@link Midnight} instance. Don't use - use {@link Midnight#get()} instead.
     */
    public static final Midnight MIDNIGHT = DistExecutor.safeRunForDist(() -> MidnightClient::dataOrClient, () -> MidnightServer::new);

    public MidnightMod() {
        printVersion();

        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::loadComplete);

        MinecraftForge.EVENT_BUS.register(MIDNIGHT);
        FMLJavaModLoadingContext.get().getModEventBus().register(MIDNIGHT);

        MIDNIGHT.preInit();
        LOGGER.debug("Midnight pre-initialized");
    }

    private void printVersion() {
        LOGGER.info("Initializing The Midnight");
        LOGGER.info(" - Version: " + MidnightInfo.VERSION);
        LOGGER.info(" - Build Date: " + MidnightInfo.BUILD_DATE);
        LOGGER.info(" - Dist: " + FMLEnvironment.dist);

        if (MidnightInfo.IDE && !MidnightInfo.TESTSERVER) {
            LOGGER.info(" - Running in an IDE or via Gradle");
        } else if (MidnightInfo.TESTSERVER) {
            LOGGER.info(" - Running a GitHub Actions test server");
        }

        if (MidnightInfo.DATAGEN) {
            LOGGER.info(" - Running data generator");
        }
    }

    private void setup(FMLCommonSetupEvent event) {
        MIDNIGHT.init();
        LOGGER.debug("Midnight initialized");
    }

    private void loadComplete(FMLLoadCompleteEvent event) {
        MIDNIGHT.postInit();
        LOGGER.debug("Midnight post-initialized");
    }
}
